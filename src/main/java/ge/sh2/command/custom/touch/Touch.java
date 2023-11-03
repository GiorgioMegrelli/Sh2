package ge.sh2.command.custom.touch;

import ge.sh2.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;

@Command
public class Touch implements CommandInvokable {

    @Parameters
    private Object params;

    @Override
    public void invoke() throws Exception {}

}
