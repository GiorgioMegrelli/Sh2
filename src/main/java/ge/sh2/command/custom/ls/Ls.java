package ge.sh2.command.custom.ls;

import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;

@Command(description = "Lists files in a directory")
public class Ls implements CommandInvokable {

    @Parameters
    private Object params;

    @Override
    public void invoke() throws Exception {}

}