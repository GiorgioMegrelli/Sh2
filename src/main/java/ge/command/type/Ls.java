package ge.command.type;

import ge.command.CommandInvokable;
import ge.core.annotation.Command;
import ge.core.annotation.Parameters;

@Command
public class Ls implements CommandInvokable {

    @Parameters
    private Object params;

    @Override
    public void invoke() {}

}
