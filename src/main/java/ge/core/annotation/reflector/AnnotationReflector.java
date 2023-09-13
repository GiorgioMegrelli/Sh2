package ge.core.annotation.reflector;

import ge.utils.Classes;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationReflector {

    private final Set<Class<?>> classes;

    public AnnotationReflector() {
        this("");
    }

    public AnnotationReflector(String packageName) {
        try {
            classes = Classes.loadClassesByPackage(packageName);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Class<?>> getClassesAnnotatedWith(Class<?> annotation) {
        return classes.stream()
                .filter(cls -> {
                    return classContainsAnnotation(cls, annotation);
                })
                .collect(Collectors.toUnmodifiableSet());
    }

    private boolean classContainsAnnotation(Class<?> cls, Class<?> annotationToFind) {
        Annotation[] annotations = cls.getAnnotations();
        for(Annotation annotation: annotations) {
            if(annotation.annotationType().equals(annotationToFind)) {
                return true;
            }
        }
        return false;
    }

}
