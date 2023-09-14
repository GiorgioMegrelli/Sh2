package ge.command.type;

import ge.command.CommandInvokable;
import ge.core.annotation.Command;
import ge.core.annotation.Parameters;

@Command
public class Find implements CommandInvokable {

    @Parameters
    private Object params;

    @Override
    public void invoke() {}

}
