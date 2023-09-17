package ge.utils;

import java.util.Objects;

public class Types {

    public static Object toType(String value, Class<?> type) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(type);

        if(type.equals(String.class)) {
            return value;
        } else if(type.equals(boolean.class) || type.equals(Boolean.class)) {
            return value.equalsIgnoreCase("true");
        } else if(type.equals(short.class) || type.equals(Short.class)) {
            return Short.parseShort(value);
        } else if(type.equals(int.class) || type.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if(type.equals(long.class) || type.equals(Long.class)) {
            return Long.parseLong(value);
        }

        throw new RuntimeException("Unknown type: " + type);
    }

}
