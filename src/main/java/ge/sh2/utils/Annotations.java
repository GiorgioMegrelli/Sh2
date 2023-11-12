package ge.sh2.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Annotations {

    public static boolean containsAnnotation(Class<?> cls, Class<?> annotationToFind) {
        Annotation[] annotations = cls.getAnnotations();
        return isInArray(annotations, annotationToFind);
    }

    public static boolean containsAnnotation(Method method, Class<?> annotationToFind) {
        Annotation[] annotations = method.getAnnotations();
        return isInArray(annotations, annotationToFind);
    }

    private static boolean isInArray(Annotation[] annotations, Class<?> annotationToFind) {
        for(Annotation annotation: annotations) {
            if(annotation.annotationType() == annotationToFind) {
                return true;
            }
        }
        return false;
    }

}
