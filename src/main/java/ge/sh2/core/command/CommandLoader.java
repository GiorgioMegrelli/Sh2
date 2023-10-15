package ge.sh2.core.command;

import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.reflector.AnnotationReflector;
import ge.sh2.core.command.store.CommandsStore;
import ge.sh2.core.object.command.CommandObject;

public class CommandLoader {

    public static final String SH2_PACKAGE = "ge.sh2.command.sh2";
    public static final String CUSTOM_PACKAGE = "ge.sh2.command.custom";

    public static CommandsStore loadSh2Commands() {
        return loadCommands(SH2_PACKAGE, false);
    }

    public static CommandsStore loadCustomCommands() {
        return loadCommands(CUSTOM_PACKAGE, true);
    }

    private static CommandsStore loadCommands(String packageName, boolean customCommand) {
        AnnotationReflector reflector = new AnnotationReflector(packageName);
        CommandsStore commandsMap = new CommandsStore();

        for(Class<?> cls : reflector.getClassesAnnotatedWith(Command.class)) {
            commandsMap.store(new CommandObject(cls, customCommand));
        }

        return commandsMap;
    }

}
