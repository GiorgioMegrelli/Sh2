package ge.sh2.core.context;

import ge.sh2.core.command.loader.CommandLoader;
import ge.sh2.core.command.store.CommandsStore;
import ge.sh2.core.command.store.ICommandsStore;
import ge.sh2.core.command.store.UnmodifiableCommandsStore;
import ge.sh2.core.context.io.StandardSh2ContextIO;
import ge.sh2.core.context.io.Sh2ContextIO;
import ge.sh2.utils.exception.Sh2RuntimeException;

public class Sh2Context {

    private static final ICommandsStore COMMANDS;
    private static final Sh2ContextIO DEFAULT_IO;

    static {
        try {
            CommandsStore commandsStore = new CommandsStore();
            commandsStore.merge(CommandLoader.loadInternalCommands());
            commandsStore.merge(CommandLoader.loadCustomCommands());
            COMMANDS = new UnmodifiableCommandsStore(commandsStore);
        } catch (Exception e) {
            throw new Sh2RuntimeException(e);
        }

        DEFAULT_IO = new StandardSh2ContextIO();
    }

    public static ICommandsStore getCommands() {
        return COMMANDS;
    }

    public static Sh2ContextIO IO() {
        return DEFAULT_IO;
    }

}
