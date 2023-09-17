package ge.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterField {

    String name();

    String description() default "";

    boolean isRequired() default false;

}
