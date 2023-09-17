package ge.sh2.core.annotation.reflector;

import ge.sh2.utils.Annotations;
import ge.sh2.utils.ClassSet;
import ge.sh2.utils.Classes;

import java.io.IOException;
import java.util.Set;
import java.util.function.Predicate;

public class AnnotationReflector {

    private final Set<Class<?>> classes;

    public AnnotationReflector() throws IOException {
        this("");
    }

    public AnnotationReflector(String packageName) throws IOException {
        classes = Classes.loadClassesByPackage(packageName);
    }

    public ClassSet getClassesAnnotatedWith(Class<?> annotation) {
        return filterClassesWith(cls -> {
            return Annotations.containsAnnotation(cls, annotation);
        });
    }

    public ClassSet getSubTypesOfInterface(Class<?> parentInterface) {
        return filterClassesWith(cls -> {
            return Classes.isSubClassOfInterface(cls, parentInterface);
        });
    }

    private ClassSet filterClassesWith(Predicate<Class<?>> filter) {
        return classes.stream()
                .filter(filter)
                .collect(ClassSet.collector());
    }

}
