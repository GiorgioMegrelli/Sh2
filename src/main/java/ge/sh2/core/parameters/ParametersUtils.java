package ge.sh2.core.parameters;

import ge.sh2.utils.Sets;
import ge.sh2.utils.exception.BadStructureParametersException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static ge.sh2.utils.Strings.replaceStart;

public class ParametersUtils {

    public static final String GETTER_PREFIX = "get";
    public static final String SETTER_PREFIX = "set";
    public static final String SETTER_ARGUMENT_NAME;
    public static final String GETTER_ARGUMENT_NAME;
    private static final Set<Class<?>> VALID_TYPES = Sets.toSet(
            String.class, boolean.class, short.class, int.class, long.class
    );
    private static final Class<?> ARGUMENT_TYPE = String[].class;

    static {
        String arguments = "Arguments";
        SETTER_ARGUMENT_NAME = SETTER_PREFIX + arguments;
        GETTER_ARGUMENT_NAME = GETTER_PREFIX + arguments;
    }

    public static <T> List<GetterAndSetter> findValidGettersSetters(Class<T> cls) throws BadStructureParametersException {
        Method[] methods = cls.getDeclaredMethods();
        Set<Method> getters = new HashSet<>();
        for(Method method: methods) {
            String name = method.getName();
            Class<?> retType = method.getReturnType();
            Class<?>[] paramTypes = method.getParameterTypes();

            if(GETTER_ARGUMENT_NAME.equals(name)
                    && paramTypes.length == 0
                    && retType.equals(ARGUMENT_TYPE)) {
                getters.add(method);
            } else if(name.startsWith(GETTER_PREFIX)
                    && paramTypes.length == 0
                    && !retType.equals(void.class)) {
                if(!VALID_TYPES.contains(retType)) {
                    String msg = "Invalid field type: " + retType;
                    throw new BadStructureParametersException(cls, msg);
                }
                getters.add(method);
            }
        }

        List<GetterAndSetter> result = new ArrayList<>();
        Map<String, Method> gettersNames = new HashMap<>() {{
            getters.forEach(m -> {
                put(m.getName(), m);
            });
        }};
        for(Method method: methods) {
            String name = method.getName();
            if(!name.startsWith(SETTER_PREFIX)) {
                continue;
            }
            String getterName = replaceStart(name, SETTER_PREFIX, GETTER_PREFIX);
            if(!gettersNames.containsKey(getterName)) {
                String msg = "Setter method is not found for " + getterName;
                throw new BadStructureParametersException(cls, msg);
            }

            Method getter = gettersNames.get(getterName);
            Class<?>[] paramTypes = method.getParameterTypes();
            if(SETTER_ARGUMENT_NAME.equals(getterName)
                    && paramTypes.length == 1
                    && paramTypes[0].equals(ARGUMENT_TYPE)) {
                result.add(new GetterAndSetter(getter, method));
            } else if(paramTypes.length == 1 && paramTypes[0].equals(getter.getReturnType())) {
                result.add(new GetterAndSetter(getter, method));
            } else {
                String msg = "Invalid setter method for " + getterName;
                throw new BadStructureParametersException(cls, msg);
            }
        }

        return result;
    }

    public static String stringify(Object parameters) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Method> methods = findValidGettersSetters(parameters.getClass())
                .stream()
                .map(w -> w.getter)
                .collect(Collectors.toList());
        Optional<Method> argumentGetter = methods.stream()
                .filter(m -> m.getName().equals(GETTER_ARGUMENT_NAME))
                .findFirst();

        if(argumentGetter.isPresent()) {
            Method getterMethod = argumentGetter.get();
            String[] args = (String[]) getterMethod.invoke(parameters);
            sb.append("args=");
            if(args == null) {
                sb.append("[]");
            } else {
                sb.append(Arrays.asList(args));
            }
            sb.append(' ');
        }

        boolean isFirst = true;
        sb.append("options=").append('[');
        for(Method method: methods) {
            String name = method.getName();
            if(name.equals(GETTER_ARGUMENT_NAME)) {
                continue;
            }

            if(!isFirst) {
                sb.append(", ");
            }
            Object value = method.invoke(parameters);
            sb.append(name).append(": ").append(value);
            isFirst = false;
        }
        sb.append(']');

        return sb.toString();
    }

}
