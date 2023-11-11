package ge.sh2.core.annotation.reflector;

import ge.sh2.utils.Annotations;
import ge.sh2.utils.ClassSet;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class AnnotationReflector {

    private static final Scanners SUB_TYPES_FILTER = Scanners.SubTypes.filterResultsBy(s -> true);

    private final Set<Class<?>> classes = new HashSet<>();

    public AnnotationReflector(String packageName) {
        this(new Reflections(packageName));
    }

    private AnnotationReflector(Reflections reflections) {
        for(String clsName: reflections.getAll(SUB_TYPES_FILTER)) {
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
