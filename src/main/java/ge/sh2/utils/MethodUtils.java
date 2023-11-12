package ge.sh2.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Character.isUpperCase;

public class MethodUtils {

    public static final String IS_PREFIX = "is";
    public static final String GET_PREFIX = "get";
    public static final String SET_PREFIX = "set";

    public static final String[] PREFIXES = {IS_PREFIX, GET_PREFIX, SET_PREFIX};

    public static List<Method> getDeclaredMethods(Class<?> cls) {
        return getDeclaredMethods(cls, true);
    }

    public static List<Method> getDeclaredMethods(Class<?> cls, boolean recursive) {
        List<Method> methods = new ArrayList<>();
        while(cls != null && cls != Object.class) {
            methods.addAll(Arrays.asList(cls.getDeclaredMethods()));
            cls = cls.getSuperclass();
            if(!recursive) {
                break;
            }
        }
        return methods;
    }

    public static List<Method> getGetterAndSetters(Class<?> cls) {
        return getDeclaredMethods(cls).stream()
                .filter(MethodUtils::isSpecialMethod)
                .toList();
    }

    private static boolean isSpecialMethod(Method method) {
        String name = method.getName();
        for(String pre: PREFIXES) {
            if(name.startsWith(pre)) {
                int preLen = pre.length();
                int nameLen = name.length();
                if(preLen == nameLen || (preLen < nameLen && isUpperCase(name.charAt(preLen)))) {
                    return true;
                }
            }
        }
        return false;
    }

}
