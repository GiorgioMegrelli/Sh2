package ge.sh2.core.object.command;

import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.object.parameter.DefaultParametersObject;
import ge.sh2.core.object.parameter.IParametersObject;
import ge.sh2.core.object.parameter.ParameterFieldWrapper;
import ge.sh2.utils.Annotations;
import ge.sh2.utils.Strings;
import ge.sh2.exception.BadStructureCommandException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandObject {

    private final Class<?> cls;
    private final Command command;
    private final String name;
    private final Field paramsField;
    private final boolean customCommand;

    public CommandObject(Class<?> cls) throws BadStructureCommandException {
        this(cls, true);
    }

    public CommandObject(Class<?> cls, boolean customCommand) throws BadStructureCommandException {
        if(!Annotations.containsAnnotation(cls, Command.class)) {
            String reason = "Class is not annotated with " + Command.class.getSimpleName();
            throw new BadStructureCommandException(cls.getName(), reason);
        }
        if(!CommandInvokable.class.isAssignableFrom(cls)) {
            String reason = "Class is not sub type of " + CommandInvokable.class.getSimpleName();
            throw new BadStructureCommandException(cls.getName(), reason);
        }
        List<Field> paramFields = getParametersField(cls);
        if(paramFields.isEmpty()) {
            this.paramsField = null;
        } else if(paramFields.size() == 1) {
            this.paramsField = paramFields.get(0);
        } else {
            String reason = "Class has more than one field annotated with " + Parameters.class.getSimpleName();
            throw new BadStructureCommandException(cls.getName(), reason);
        }

        this.cls = cls;
        this.command = cls.getAnnotation(Command.class);
        this.name = findName();
        this.customCommand = customCommand;
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

    public void run(Object params) throws Exception {
        Object instance = cls.getDeclaredConstructor().newInstance();
        if(paramsField != null && params != null) {
            paramsField.setAccessible(true);
            paramsField.set(instance, params);
        }
        CommandInvokable commandInvokable = (CommandInvokable) cls.cast(instance);
        commandInvokable.invoke();
    }

    public Command getCommand() {
        return command;
    }

    public Field getParametersField() {
        return paramsField;
    }

    public IParametersObject getParametersObject() throws Exception {
        if(paramsField == null) {
            return new NoDefaultParametersObject();
        }
        return new DefaultParametersObject(paramsField);
    }

    public String getName() {
        return name;
    }

    public boolean isCustomCommand() {
        return customCommand;
    }

    @Override
    public String toString() {
        return "CommandObject[" + getName() + "]";
    }

    private static class NoDefaultParametersObject implements IParametersObject {
        @Override
        public Object parse(String[] args) throws Exception {
            return null;
        }

        @Override
        public Map<String, ParameterFieldWrapper> getParameters() {
            return Collections.emptyMap();
        }

        @Override
        public boolean hasArguments() {
            return false;
        }
    }

}
