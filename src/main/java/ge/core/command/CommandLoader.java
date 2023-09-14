package ge.core.command;

import ge.command.CommandInvokable;
import ge.core.annotation.Command;
import ge.core.annotation.reflector.AnnotationReflector;
import ge.core.object.CommandObject;
import ge.utils.exception.BadStructureCommandException;
import ge.utils.exception.CommandNameDuplicateException;

import java.util.HashMap;
import java.util.Map;

public class CommandLoader {

    public static Map<String, CommandObject> loadValidCommands(String packageName) throws Exception {
        AnnotationReflector reflector = new AnnotationReflector(packageName);
        Map<String, CommandObject> commandsMap = new HashMap<>();

        for(Class<?> cls : reflector.getClassesAnnotatedWith(Command.class)) {
            CommandObject command = new CommandObject(cls);
            String name = command.getName();
            if(commandsMap.containsKey(name)) {
                throw new CommandNameDuplicateException(name);
            }
            commandsMap.put(name.toLowerCase(), command);
            command.getOptions();
        }

        return commandsMap;
    }

}
