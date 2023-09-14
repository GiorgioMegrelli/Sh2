package ge.core.annotation.reflector;

import ge.utils.ClassSet;
import ge.utils.Classes;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AnnotationReflector {

    private final Set<Class<?>> classes;

    public AnnotationReflector() throws IOException {
        this("");
    }

    public AnnotationReflector(String packageName) throws IOException {
        classes = Classes.loadClassesByPackage(packageName);
    }

    public ClassSet getSubTypesOfClass(Class<?> parentClass) {
        return classes.stream()
                .filter(cls -> {
                    ClassSet parentClasses = collectParentClassesRecursively(cls);
                    return parentClasses.contains(parentClass);
                })
                .collect(ClassSet.collector());
    }

    public ClassSet getSubTypesOfInterface(Class<?> parentInterface) {
        return classes.stream()
                .filter(cls -> {
                    ClassSet parentClasses = collectParentInterfacesRecursively(cls);
                    return parentClasses.contains(parentInterface);
                })
                .collect(ClassSet.collector());
    }

    private static ClassSet collectParentClassesRecursively(Class<?> forCls) {
        ClassSet parentClasses = new ClassSet();
        Class<?> curr = forCls;
        while(curr != Object.class) {
            curr = curr.getSuperclass();
            parentClasses.add(curr);
        }
        return parentClasses;
    }

    private static ClassSet collectParentInterfacesRecursively(Class<?> forCls) {
        ClassSet parentInterfaces = new ClassSet();
        Queue<Class<?>> queue = new LinkedList<>();
        queue.add(forCls);

        while(!queue.isEmpty()) {
            Class<?> curr = queue.poll();
            Class<?>[] interfaces = curr.getInterfaces();
            for(Class<?> i: interfaces) {
                if(!parentInterfaces.contains(i)) {
                    queue.add(i);
                    parentInterfaces.add(i);
                }
            }
        }
        return parentInterfaces;
    }

}
