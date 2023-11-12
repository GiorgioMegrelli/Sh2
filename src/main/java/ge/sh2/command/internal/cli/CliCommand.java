package ge.sh2.command.internal.cli;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.command.CommandInvokable;

@Command(name = "cli")
public class CliCommand implements CommandInvokable {
    @Override
    public void invoke() throws Exception {
        Sh2Context.getIO().println("Is not available yet ;(");
    }
}
