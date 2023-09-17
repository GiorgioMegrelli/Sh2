package ge.core;

import ge.core.command.CommandLoader;
import ge.core.object.CommandObject;
import ge.core.object.ParametersObject;
import ge.core.parameters.ParametersUtils;
import ge.utils.ArrayFunctions;

import java.util.Map;

public class Executor {

    private static final String PACKAGE_NAME = "ge.command.type";

    public void execute(String[] args) throws Exception {
        Map<String, CommandObject> commands = CommandLoader.loadValidCommands(PACKAGE_NAME);
        Class<?> optionsType = commands.get(args[0]).getOptionsType();

        args = ArrayFunctions.shift(args);
        Object params = new ParametersObject(optionsType).parse(args);
        System.out.println(ParametersUtils.stringify(params));
    }

}
