package ge.sh2.core.parameters;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ge.sh2.core.parameters.ParametersUtils.*;

public class ParametersExtendedUtils {

    public static String stringify(Object parameters) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<Method> methods = findValidGettersSetters(parameters.getClass())
                .values()
                .stream()
                .map(w -> w.getter)
                .toList();
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
