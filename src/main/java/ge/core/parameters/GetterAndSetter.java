package ge.core.parameters;

import java.lang.reflect.Method;

public class GetterAndSetter {
    public final Method getter, setter;

    public GetterAndSetter(Method getter, Method setter) {
        this.getter = getter;
        this.setter = setter;
    }
}
