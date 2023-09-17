package ge.sh2.utils;

import java.lang.annotation.Annotation;

public class Annotations {

    public static boolean containsAnnotation(Class<?> cls, Class<?> annotationToFind) {
        Annotation[] annotations = cls.getAnnotations();
        for(Annotation annotation: annotations) {
            if(annotation.annotationType() == annotationToFind) {
                return true;
            }
        }
        return false;
    }

}
