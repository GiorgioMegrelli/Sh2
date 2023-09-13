package ge.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClasses {

    @Test
    public void testLoadClassesByPackage() throws IOException {
        Set<Class<?>> classes = Classes.loadClassesByPackage();
        assertTrue(classes.contains(Classes.class));
        assertTrue(classes.contains(TestClasses.class));
    }

    @Test
    public void testLoadClassesByPackageWithName() throws IOException {
        Set<Class<?>> classes = Classes.loadClassesByPackage("ge.utils");
        assertTrue(classes.contains(Classes.class));
        assertTrue(classes.contains(TestClasses.class));
    }

    @Test
    public void testLoadClassesByPackageEmpty() throws IOException {
        Set<Class<?>> classes = Classes.loadClassesByPackage("bad.package.name");
        assertTrue(classes.isEmpty());
    }

}
