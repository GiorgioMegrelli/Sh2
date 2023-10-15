package ge.sh2.core;

import ge.sh2.core.command.CommandLoader;
import ge.sh2.core.command.store.CommandsStore;
import ge.sh2.core.command.store.ICommandsStore;
import ge.sh2.core.command.store.UnmodifiableCommandsStore;
import ge.sh2.utils.exception.Sh2RuntimeException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Objects;

public class Sh2Context {

    private static PrintStream stdout = System.out;
    private static InputStream stdin = System.in;
    private static final ICommandsStore COMMANDS;

    static {
        try {
            CommandsStore commandsStore = new CommandsStore();
            commandsStore.merge(CommandLoader.loadSh2Commands());
            commandsStore.merge(CommandLoader.loadCustomCommands());
            COMMANDS = new UnmodifiableCommandsStore(commandsStore);
        } catch (Exception e) {
            throw new Sh2RuntimeException(e);
        }
    }

    public static ICommandsStore getCommands() {
        return COMMANDS;
    }

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
