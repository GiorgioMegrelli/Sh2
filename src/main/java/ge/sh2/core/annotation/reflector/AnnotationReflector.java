package ge.sh2.core.annotation.reflector;

import ge.sh2.utils.Annotations;
import ge.sh2.utils.ClassSet;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class AnnotationReflector {

    private static final SubTypesScanner SUB_TYPES_SCANNER = new SubTypesScanner(false);

    private final Set<Class<?>> classes = new HashSet<>();

    public AnnotationReflector() {
        this(new Reflections(SUB_TYPES_SCANNER));
    }

    public AnnotationReflector(String packageName) {
        this(new Reflections(SUB_TYPES_SCANNER, packageName));
    }

    private AnnotationReflector(Reflections reflections) {
        for(String clsName: reflections.getAllTypes()) {
            try {
                classes.add(Class.forName(clsName));
            } catch (ClassNotFoundException ignored) {}
        }
    }

    public ClassSet getClassesAnnotatedWith(Class<?> annotation) {
        return filterClassesWith(cls -> {
            return Annotations.containsAnnotation(cls, annotation);
        });
    }

    private ClassSet filterClassesWith(Predicate<Class<?>> filter) {
        return classes.stream()
                .filter(filter)
                .collect(ClassSet.collector());
    }

}
