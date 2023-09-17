package ge.sh2.core;

import ge.sh2.core.command.CommandLoader;
import ge.sh2.core.object.CommandObject;
import ge.sh2.core.object.ParametersObject;
import ge.sh2.core.parameters.ParametersUtils;
import ge.sh2.utils.ArrayFunctions;

import java.util.Map;

public class Executor {

    private static final String PACKAGE_NAME = "ge.sh2.command.type";

    public void execute(String[] args) throws Exception {
        Map<String, CommandObject> commands = CommandLoader.loadValidCommands(PACKAGE_NAME);
        Class<?> optionsType = commands.get(args[0]).getOptionsType();

        args = ArrayFunctions.shift(args);
        Object params = new ParametersObject(optionsType).parse(args);
        System.out.println(ParametersUtils.stringify(params));
    }

}
