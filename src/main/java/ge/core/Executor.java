package ge.core;

import ge.command.AbstractCommand;
import ge.command.ICommand;
import ge.core.annotation.reflector.AnnotationReflector;
import ge.utils.ClassSet;

import java.io.IOException;

public class Executor {

    private static final String PACKAGE_NAME = "ge.command.type";

    public static void execute(String[] args) {
        try {
            AnnotationReflector reflector = new AnnotationReflector(PACKAGE_NAME);

            ClassSet mergedClasses = ClassSet.merge(
                    reflector.getSubTypesOfClass(AbstractCommand.class),
                    reflector.getSubTypesOfInterface(ICommand.class)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
