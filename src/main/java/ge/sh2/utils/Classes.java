package ge.sh2.utils;

import ge.sh2.utils.filter.IPathFilter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Classes {

    public static final String CLASS_FILE_EXT = ".class";
    public static final char PACKAGE_NAME_SEP = '.';

    public static Set<Class<?>> loadClassesByPackage() throws IOException {
        return loadClassesByPackage("");
    }

    public static Set<Class<?>> loadClassesByPackage(String packageName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String packagePath = Strings.replaceAll(packageName, PACKAGE_NAME_SEP, '/');
        Enumeration<URL> resources = classLoader.getResources(packagePath);
        IPathFilter classFileFilters = (file) -> {
            return file.getName().endsWith(CLASS_FILE_EXT);
        };

        Set<Class<?>> classes = new HashSet<>();
        while(resources.hasMoreElements()) {
            File root = new File(resources.nextElement().getFile());
            Paths.walkFiles(root, classFileFilters).forEach(file -> {
                String rootName = root.getAbsolutePath() + File.separator;
                String path = Strings.replaceStart(file.getAbsolutePath(), rootName)
                        .replace(CLASS_FILE_EXT, "");
                String clsName = Strings.replaceAll(path, File.separatorChar, PACKAGE_NAME_SEP);
                if(!packageName.isEmpty()) {
                    clsName = packageName + PACKAGE_NAME_SEP + clsName;
                }
                try {
                    classes.add(Class.forName(clsName, false, classLoader));
                } catch (ClassNotFoundException ignored) {}
            });
        }
        return classes;
    }

    public static boolean isSubClassOfInterface(Class<?> cls, Class<?> interfaceCls) {
        return isSubClassOfInterface(cls, interfaceCls, 1);
    }

    public static boolean isSubClassOfInterface(Class<?> cls, Class<?> interfaceCls, int depth) {
        if(depth <= 0) {
            throw new IllegalArgumentException("Invalid depth: " + depth);
        }

        Queue<Class<?>> queue = new LinkedList<>();
        queue.add(cls);

        while(!queue.isEmpty()) {
            Class<?> curr = queue.poll();
            if(curr.equals(interfaceCls)) {
                return true;
            }

            if(depth > 0) {
                queue.addAll(Arrays.asList(curr.getInterfaces()));
                depth--;
            }
        }

        return false;
    }

}
