package ge.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClassSet {

    private static class ClassA {}

    private static class ClassB {}

    private static class ClassC {}

    @Test
    public void testClassSetCollector() {
        final List<Class<?>> classes = Arrays.asList(ClassA.class, ClassB.class, ClassC.class);

        ClassSet classSet = classes.stream().collect(ClassSet.collector());

        assertEquals(classSet.size(), classes.size());
        for(Class<?> cls: classes) {
            assertTrue(classSet.contains(cls));
        }
    }

}
