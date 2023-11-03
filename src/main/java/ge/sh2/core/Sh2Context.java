package ge.sh2.core;

import ge.sh2.core.command.CommandLoader;
import ge.sh2.core.command.store.CommandsStore;
import ge.sh2.core.command.store.ICommandsStore;
import ge.sh2.core.command.store.UnmodifiableCommandsStore;
import ge.sh2.utils.exception.Sh2RuntimeException;

public class Sh2Context {

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

}
