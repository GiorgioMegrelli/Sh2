package ge.sh2.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static ge.sh2.utils.Annotations.containsAnnotation;

public class TestAnnotations {

    @Retention(RetentionPolicy.RUNTIME)
    private @interface FakeAnnotation {}

    @FakeAnnotation
    private static class HasAnnotation {}

    private static class NotHasAnnotation {}

    @Test
    public void testContainsAnnotation() {
        Assertions.assertTrue(containsAnnotation(HasAnnotation.class, FakeAnnotation.class));
        Assertions.assertFalse(containsAnnotation(NotHasAnnotation.class, FakeAnnotation.class));
    }

}
