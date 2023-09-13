package ge.app;

import ge.core.annotation.Command;
import ge.core.annotation.reflector.AnnotationReflector;

public class Sh2Application {

    public static void run() {
        AnnotationReflector reflector = new AnnotationReflector("ge.command");
        System.out.println(reflector.getClassesAnnotatedWith(Command.class));
    }

}
