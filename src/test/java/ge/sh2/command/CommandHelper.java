package ge.sh2.command;

import ge.sh2.core.annotation.Parameters;

import java.lang.reflect.Field;

public class CommandHelper {

    public static boolean setParameters(Object command, Object parameters) throws IllegalAccessException {
        Field[] fields = command.getClass().getDeclaredFields();
        for(Field field: fields) {
            if(field.getAnnotation(Parameters.class) != null) {
                field.setAccessible(true);
                field.set(command, parameters);
                return true;
            }
        }
        return false;
    }

}
