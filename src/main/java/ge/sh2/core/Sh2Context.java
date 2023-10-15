package ge.sh2.core;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

public class Sh2Context {

    private static PrintStream stdout = System.out;
    private static InputStream stdin = System.in;

    public static void setStdout(PrintStream stdout) {
        Objects.requireNonNull(stdout);
        Sh2Context.stdout = stdout;
    }

    public static PrintStream stdout() {
        return stdout;
    }

    public static void setStdin(InputStream stdin) {
        Objects.requireNonNull(stdin);
        Sh2Context.stdin = stdin;
    }

    public static InputStream stdin() {
        return stdin;
    }

}
