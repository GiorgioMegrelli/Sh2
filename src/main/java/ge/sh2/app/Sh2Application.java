package ge.sh2.app;

import ge.sh2.core.context.Sh2Context;
import ge.sh2.core.command.store.ICommandsStore;
import ge.sh2.core.console.style.CommonStyles;
import ge.sh2.core.console.style.ConsoleStyle;
import ge.sh2.core.object.command.CommandObject;
import ge.sh2.core.object.parameter.IParametersObject;
import ge.sh2.utils.ArrayUtils;

public class Sh2Application {

    private static final ICommandsStore commandsStore = Sh2Context.getCommands();

    private static void printLogo() {
        try {
            String logo = Sh2LogoLoader.loadLogo();
            Sh2Context.IO().out().println(
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
            Sh2Context.IO().err().println("Unknown command: " + commandName);
            return;
        }

        IParametersObject parameters = commandObject.getParametersObject();

        args = ArrayUtils.shift(args);
        Object params = parameters.parse(args);
        commandObject.run(params);
    }

}
