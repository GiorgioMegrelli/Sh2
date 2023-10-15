package ge.sh2.core.command;

import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.reflector.AnnotationReflector;
import ge.sh2.core.object.CommandObject;
import ge.sh2.utils.exception.CommandNameDuplicateException;
import ge.sh2.utils.exception.Sh2RuntimeException;

import java.util.HashMap;
import java.util.Map;

public class CommandLoader {

    private static void put(Map<String, CommandObject> map, String key, CommandObject value) {
        if(map.containsKey(key)) {
            throw new Sh2RuntimeException("Duplicate name: " + key);
        }
        map.put(key, value);
    }

    public static Map<String, CommandObject> loadValidCommands(String packageName) throws Exception {
        AnnotationReflector reflector = new AnnotationReflector(packageName);
        Map<String, CommandObject> commandsMap = new HashMap<>();

        for(Class<?> cls : reflector.getClassesAnnotatedWith(Command.class)) {
            CommandObject command = new CommandObject(cls);
            String name = command.getName();
            if(commandsMap.containsKey(name)) {
                throw new CommandNameDuplicateException(name);
            }
            put(commandsMap, name.toLowerCase(), command);
        }

        return commandsMap;
    }

}
