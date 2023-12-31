package ge.sh2.command.internal.help;

import ge.sh2.core.context.Sh2Context;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.object.command.CommandObject;

import java.util.List;

@Command(name="cmd-list")
public class CommandList implements CommandInvokable {

    @Parameters
    private CmdListParameters parameters;

    @Override
    public void invoke() throws Exception {
        List<CommandObject> commands;
        if(parameters.isAll()) {
            commands = Sh2Context.getCommands().getAll();
        } else {
            commands = Sh2Context.getCommands().getCustoms();
        }

        for(CommandObject command: commands) {
            Sh2Context.IO().out().println(command.getName());
        }
    }

}
