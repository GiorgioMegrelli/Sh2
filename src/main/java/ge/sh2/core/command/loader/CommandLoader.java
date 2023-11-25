package ge.sh2.core.command.loader;

import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.reflector.AnnotationReflector;
import ge.sh2.core.command.store.CommandsStore;
import ge.sh2.core.object.command.CommandObject;

import java.util.Set;

public class CommandLoader {

    private static final String COMMANDS_CONFIG_XML = "configurations/commands.xml";
    private static final Set<String> INTERNAL_PACKAGES;
    private static final Set<String> CUSTOM_PACKAGES;

    static {
        try {
            CommandsConfigReader configReader = new CommandsConfigReader(COMMANDS_CONFIG_XML);
            INTERNAL_PACKAGES = configReader.getInternalPackages();
            CUSTOM_PACKAGES = configReader.getCustomPackages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static CommandsStore loadInternalCommands() {
        return loadCommands(INTERNAL_PACKAGES, false);
    }

    public static CommandsStore loadCustomCommands() {
        return loadCommands(CUSTOM_PACKAGES, true);
    }

    private static CommandsStore loadCommands(Set<String> packageNames, boolean customCommand) {
        CommandsStore commandsMap = new CommandsStore();
        for(String packageName: packageNames) {
            AnnotationReflector reflector = new AnnotationReflector(packageName);
            for(Class<?> cls : reflector.getClassesAnnotatedWith(Command.class)) {
                commandsMap.store(new CommandObject(cls, customCommand));
            }
        }
        return commandsMap;
    }

}
