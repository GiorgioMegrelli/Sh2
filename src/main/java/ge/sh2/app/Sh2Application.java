package ge.sh2.app;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.command.store.ICommandsStore;
import ge.sh2.core.console.style.CommonStyles;
import ge.sh2.core.console.style.ConsoleStyle;
import ge.sh2.core.object.command.CommandObject;
import ge.sh2.core.object.parameter.IParametersObject;
import ge.sh2.utils.ArrayFunctions;
import ge.sh2.utils.exception.UnknownCommand;

public class Sh2Application {

    private static final ICommandsStore commandsStore = Sh2Context.getCommands();

    private static void printLogo() {
        try {
            String logo = Sh2LogoLoader.loadLogo();
            Sh2Context.getIO().println(
                    ConsoleStyle.stylize(logo, CommonStyles.BOLD)
            );
        } catch (Exception ignored) {}
    }

    public static void run(String[] args) throws Exception {
        if(args.length == 0) {
            printLogo();
            return;
        }

        String commandName = args[0];
        CommandObject commandObject = commandsStore.get(commandName);
        if(commandObject == null) {
            throw new UnknownCommand(commandName);
        }

        IParametersObject parameters = commandObject.getParametersObject();

        args = ArrayFunctions.shift(args);
        Object params = parameters.parse(args);
        commandObject.run(params);
    }

}
