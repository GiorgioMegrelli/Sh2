package ge.sh2.app;

import java.io.IOException;
import java.io.InputStream;

public class Sh2LogoLoader {

    private static final String LOGO_FILE_PATH = "logo/cmd-logo.txt";

    private static InputStream loadLogoAsStream() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(LOGO_FILE_PATH);
    }

    public static String loadLogo() throws IOException {
        try(InputStream inputStream = loadLogoAsStream()) {
            byte[] data = inputStream.readAllBytes();
            return new String(data);
        }
    }

}
