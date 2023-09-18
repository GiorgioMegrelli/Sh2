package ge.sh2.command.type;

import ge.sh2.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;

@Command
public class Ls implements CommandInvokable {

    @Parameters
    private Object params;

    @Override
    public void invoke() throws Exception {}

}
