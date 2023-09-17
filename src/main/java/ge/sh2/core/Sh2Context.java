package ge.sh2.core;

import java.io.InputStream;
import java.io.PrintStream;

public class Sh2Context {

    public static PrintStream stdout() {
        return System.out;
    }

    public static InputStream stdin() {
        return System.in;
    }

}
