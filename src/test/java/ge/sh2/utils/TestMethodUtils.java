package ge.sh2.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

import static ge.sh2.utils.MethodUtils.*;

public class TestMethodUtils {

    private static class TestSuperClass {
        public void isA() {}
        public void getA() {}
        public void setA() {}
        public void otherMethod() {}
    }

    private static class TestClass extends TestSuperClass {
        public void isM() {}
        public void getM() {}
        public void setM() {}
        public void otherMethod2() {}
    }

    private static final Set<String> VALID_METHODS = Sets.toSet(
            "isA", "getA", "setA",
            "isM", "getM", "setM"
    );
    private static final Set<String> INVALID_METHODS = Sets.toSet("otherMethod", "otherMethod2");

    @Test
    public void testGetDeclaredMethods() {
        Set<String> names = getDeclaredMethods(TestClass.class)
                .stream()
                .map(Method::getName)
                .collect(Collectors.toSet());

        Assertions.assertEquals(names, Sets.merge(VALID_METHODS, INVALID_METHODS));
    }

    @Test
    public void testGetGetterAndSetters() {
        Set<String> names = getGetterAndSetters(TestClass.class)
                .stream()
                .map(Method::getName)
                .collect(Collectors.toSet());

        Assertions.assertEquals(names, VALID_METHODS);
    }

}
