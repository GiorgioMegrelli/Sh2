package ge.sh2.command.custom.find;

import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;

@Command
public class Find implements CommandInvokable {

    @Parameters
    private Object params;

    @Override
    public void invoke() throws Exception {}

}
