package ge.sh2.core;

import ge.sh2.core.command.CommandLoader;
import ge.sh2.core.object.CommandObject;
import ge.sh2.core.object.ParametersObject;
import ge.sh2.utils.ArrayFunctions;

import java.util.Map;

public class Executor {

    private static final String PACKAGE_NAME = "ge.sh2.command.type";

    public static void execute(String[] args) throws Exception {
        Map<String, CommandObject> commands = CommandLoader.loadValidCommands(PACKAGE_NAME);
        String commandName = args[0];
        CommandObject commandObject = commands.get(commandName);
        Class<?> optionsType = commandObject.getOptionsType();

        args = ArrayFunctions.shift(args);
        Object params = new ParametersObject(optionsType).parse(args);
        commandObject.run(params);
    }

}
