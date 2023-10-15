package ge.sh2.core.object.parameter;

import java.lang.reflect.Method;

public class ParameterFieldWrapper {

    public final String name;
    public final String description;
    public final Class<?> type;
    public final Method getter;
    public final Method setter;
    public final boolean isRequired;

    public ParameterFieldWrapper(
            String name, String description,
            Class<?> type,
            Method getter, Method setter, boolean isRequired
    ) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.getter = getter;
        this.setter = setter;
        this.isRequired = isRequired;
    }

}
