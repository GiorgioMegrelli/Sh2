package ge.sh2.core;

import ge.sh2.core.command.CommandLoader;
import ge.sh2.core.command.store.CommandsStore;
import ge.sh2.core.command.store.ICommandsStore;
import ge.sh2.core.command.store.UnmodifiableCommandsStore;
import ge.sh2.core.console.io.impl.err.StandardErrorInputOutputFactory;
import ge.sh2.core.console.io.impl.std.StandardOutputFactory;
import ge.sh2.core.console.io.InputOutput;
import ge.sh2.utils.exception.Sh2RuntimeException;

public class Sh2Context {

    private static final ICommandsStore COMMANDS;
    private static final InputOutput IO_INSTANCE_DEFAULT;
    private static final InputOutput ERR_IO_INSTANCE_DEFAULT;
    private static InputOutput IO_INSTANCE;
    private static InputOutput ERR_IO_INSTANCE;

    static {
        try {
            CommandsStore commandsStore = new CommandsStore();
            commandsStore.merge(CommandLoader.loadInternalCommands());
            commandsStore.merge(CommandLoader.loadCustomCommands());
            COMMANDS = new UnmodifiableCommandsStore(commandsStore);
        } catch (Exception e) {
            throw new Sh2RuntimeException(e);
        }

        IO_INSTANCE_DEFAULT = new StandardOutputFactory().build();
        IO_INSTANCE = IO_INSTANCE_DEFAULT;
        ERR_IO_INSTANCE_DEFAULT = new StandardErrorInputOutputFactory().build();
        ERR_IO_INSTANCE = ERR_IO_INSTANCE_DEFAULT;
    }

    public static void restore() {
        IO_INSTANCE = IO_INSTANCE_DEFAULT;
    }

    public static ICommandsStore getCommands() {
        return COMMANDS;
    }

    public static InputOutput getIO() {
        return IO_INSTANCE;
    }

    public static void setIO(InputOutput io) {
        IO_INSTANCE = io;
    }

    public static InputOutput getErrIO() {
        return ERR_IO_INSTANCE;
    }

    public static void setErrIO(InputOutput io) {
        ERR_IO_INSTANCE = io;
    }

}
