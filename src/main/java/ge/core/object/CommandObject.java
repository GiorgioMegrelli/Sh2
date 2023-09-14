package ge.core.object;

import ge.command.CommandInvokable;
import ge.core.annotation.Command;
import ge.core.annotation.Parameters;
import ge.utils.Annotations;
import ge.utils.Classes;
import ge.utils.Strings;
import ge.utils.exception.BadStructureCommandException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandObject {

    private final Class<?> cls;
    private final Command command;
    private final String name;
    private final Field paramsField;

    public CommandObject(Class<?> cls) throws BadStructureCommandException {
        if(!Annotations.containsAnnotation(cls, Command.class)) {
            String reason = "Class is not annotated with " + Command.class.getSimpleName();
            throw new BadStructureCommandException(cls.getName(), reason);
        }
        if(!Classes.isSubClassOfInterface(cls, CommandInvokable.class)) {
            String reason = "Class is not sub type of " + CommandInvokable.class.getSimpleName();
            throw new BadStructureCommandException(cls.getName(), reason);
        }
        List<Field> paramFields = getParametersField(cls);
        if(paramFields.size() != 1) {
            String reason;
            if(paramFields.size() == 0) {
                reason = "Class doesn't have field annotated with " + Parameters.class.getSimpleName();
            } else {
                reason = "Class has more than one field annotated with " + Parameters.class.getSimpleName();
            }
            throw new BadStructureCommandException(cls.getName(), reason);
        }
        this.paramsField = paramFields.get(0);

        this.cls = cls;
        this.command = cls.getAnnotation(Command.class);
        this.name = findName();
    }

    private String findName() {
        String name = command.name();
        if(Strings.isEmpty(name)) {
            return Strings.camelCaseToSnakeCase(cls.getSimpleName(), '-');
        }
        return name;
    }

    private static List<Field> getParametersField(Class<?> cls) {
        List<Field> validFields = new ArrayList<>();
        for(Field field: cls.getDeclaredFields()) {
            if(field.isAnnotationPresent(Parameters.class)) {
                validFields.add(field);
            }
        }
        return validFields;
    }

    public void run() {}

    public void getOptions() {
        Class<?> type = paramsField.getType();
        if(type != Object.class) {
            System.out.println(Arrays.toString(type.getFields()));
            System.out.println(Arrays.toString(type.getDeclaredFields()));
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CommandObject[" + getName() + "]";
    }

}