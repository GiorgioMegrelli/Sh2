package ge.sh2.core.parameters;

import ge.sh2.core.annotation.ParameterField;
import ge.sh2.utils.Annotations;
import ge.sh2.utils.Sets;
import ge.sh2.utils.Strings;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ge.sh2.utils.MethodUtils.*;

public class ParametersUtils {

    private static final String ARGUMENTS_METHOD_NAME = "arguments";
    private static final Set<Class<?>> VALID_TYPES = Sets.toSet(
            String.class, boolean.class, short.class, int.class, long.class
    );
    private static final Class<?> ARGUMENT_TYPE = String[].class;

    public static final String SETTER_ARGUMENT_NAME;
    public static final String GETTER_ARGUMENT_NAME;

    static {
        SETTER_ARGUMENT_NAME = SET_PREFIX + Strings.capitalize(ARGUMENTS_METHOD_NAME);
        GETTER_ARGUMENT_NAME = GET_PREFIX + Strings.capitalize(ARGUMENTS_METHOD_NAME);
    }

    public static <T> Map<String, GetterAndSetter> findValidGettersSetters(Class<T> cls) {
        Map<String, List<Method>> namedMethods = new HashMap<>();
        for(Method method: getGetterAndSetters(cls)) {
            String name = method.getName();
            String validName = replaceAndValidate(name);
            if(name.length() == validName.length()) {
                continue;
            }

            if(namedMethods.containsKey(validName)) {
                namedMethods.get(validName).add(method);
            } else {
                namedMethods.put(validName, new ArrayList<>(3) {{
                    add(method);
                }});
            }
        }

        Map<String, GetterAndSetter> result = new HashMap<>();
        for(Map.Entry<String, List<Method>> entry: namedMethods.entrySet()) {
            List<Method> methods = entry.getValue();
            GetterAndSetter gas = asGetterAndSetter(methods);
            if(gas == null) {
                continue;
            }

            String name = entry.getKey();
            Class<?> getterRetType = gas.getter.getReturnType();
            Class<?> setterRetType = gas.setter.getReturnType();
            Class<?>[] getterParamTypes = gas.getter.getParameterTypes();
            Class<?>[] setterParamTypes = gas.setter.getParameterTypes();
            if(name.equals(ARGUMENTS_METHOD_NAME)) {
                if(getterRetType.equals(ARGUMENT_TYPE)
                && setterRetType.equals(void.class)
                && getterParamTypes.length == 0
                && setterParamTypes.length == 1
                && setterParamTypes[0].equals(ARGUMENT_TYPE)) {
                    result.put(name, gas);
                }
            } else {
                if(Annotations.containsAnnotation(gas.getter, ParameterField.class)
                && VALID_TYPES.contains(getterRetType)
                && setterRetType.equals(void.class)
                && getterParamTypes.length == 0
                && setterParamTypes.length == 1
                && setterParamTypes[0].equals(getterRetType)) {
                    result.put(name, gas);
                }
            }
        }
        return result;
    }

    private static String replaceAndValidate(String name) {
        String validName;
        if(name.startsWith(IS_PREFIX)) {
            validName = Strings.replaceStart(name, IS_PREFIX);
        } else if(name.startsWith(GET_PREFIX)) {
            validName = Strings.replaceStart(name, GET_PREFIX);
        } else if(name.startsWith(SET_PREFIX)) {
            validName = Strings.replaceStart(name, SET_PREFIX);
        } else {
            return name;
        }
        String start = validName.substring(0, 1);
        return Strings.replaceStart(validName, start, start.toLowerCase());
    }

    private static GetterAndSetter asGetterAndSetter(List<Method> methods) {
        if(methods.size() == 2) {
            Method getter = null;
            Method setter = null;
            for(Method method: methods) {
                String name = method.getName();
                if(name.startsWith(IS_PREFIX) || name.startsWith(GET_PREFIX)) {
                    getter = method;
                } else if(name.startsWith(SET_PREFIX)) {
                    setter = method;
                }
            }
            if(getter != null && setter != null) {
                return new GetterAndSetter(getter, setter);
            }
        }
        return null;
    }

}
