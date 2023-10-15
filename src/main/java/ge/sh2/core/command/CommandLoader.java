package ge.sh2.core.command;

import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.reflector.AnnotationReflector;
import ge.sh2.core.command.store.CommandsStore;
import ge.sh2.core.object.CommandObject;

public class CommandLoader {

    public static final String PACKAGE_NAME = "ge.sh2.command.custom";

    public static CommandsStore loadSh2Commands() {
        return new CommandsStore();
    }

    public static CommandsStore loadCustomCommands() throws Exception {
        AnnotationReflector reflector = new AnnotationReflector(PACKAGE_NAME);
        CommandsStore commandsMap = new CommandsStore();

        for(Class<?> cls : reflector.getClassesAnnotatedWith(Command.class)) {
            commandsMap.store(new CommandObject(cls));
        }

        return commandsMap;
    }

}
