package ge.sh2.app;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.command.store.ICommandsStore;
import ge.sh2.core.object.CommandObject;
import ge.sh2.core.object.ParametersObject;
import ge.sh2.utils.ArrayFunctions;

public class Sh2Application {

    private static final ICommandsStore commandsStore = Sh2Context.getCommands();

    public static void run(String[] args) throws Exception {
        CommandObject commandObject = commandsStore.get(args[0]);
        Class<?> optionsType = commandObject.getOptionsType();

        args = ArrayFunctions.shift(args);
        Object params = new ParametersObject(optionsType).parse(args);
        commandObject.run(params);
    }

}
