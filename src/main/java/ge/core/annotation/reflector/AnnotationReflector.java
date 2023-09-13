package ge.core.annotation.reflector;

import ge.utils.Classes;

import java.io.IOException;

public class AnnotationReflector {

    public AnnotationReflector() {
        this("");
    }

    public AnnotationReflector(String packageName) {
        try {
            Classes.loadClassesByPackage("ge.command");
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

}
