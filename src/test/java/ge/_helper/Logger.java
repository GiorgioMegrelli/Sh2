package ge._helper;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static final String FILE = "STDOUT.txt";

    public static void clear() {
        try(FileWriter fw = new FileWriter(FILE)) {
            fw.write("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(Object obj) {
        try(FileWriter fw = new FileWriter(FILE, true)) {
            if(obj == null) {
                obj = "<null>";
            }
            fw.write(obj.toString());
            fw.write('\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
