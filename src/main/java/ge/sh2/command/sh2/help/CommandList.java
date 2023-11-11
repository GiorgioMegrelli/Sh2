package ge.sh2.command.sh2.help;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.object.command.CommandObject;

import java.util.List;

@Command(name="cmd-list")
public class CommandList implements CommandInvokable {

    @Parameters
    private HelpParameters parameters;

    @Override
    public void invoke() throws Exception {
        List<CommandObject> commands;
        if(parameters.getAll()) {
            commands = Sh2Context.getCommands().getAll();
        } else {
            commands = Sh2Context.getCommands().getCustoms();
        }

        for(CommandObject command: commands) {
            Sh2Context.getIO().println(command.getName());
        }
    }

}
