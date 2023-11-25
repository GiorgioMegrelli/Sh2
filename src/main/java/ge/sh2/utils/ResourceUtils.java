package ge.sh2.utils;

import java.io.InputStream;

public class ResourceUtils {

    public static InputStream getResourceAsStream(String name) {
        return classLoader().getResourceAsStream(name);
    }

    private static ClassLoader classLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

}
