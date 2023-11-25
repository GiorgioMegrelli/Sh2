package ge.sh2.command.custom.mkdir;

import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.command.ParametersWithArguments;

@Command(name = "mkdir", description = "Creates directories")
public class MkDir implements CommandInvokable {

    private static class MkDirParameters extends ParametersWithArguments {}

    @Parameters
    private MkDirParameters parameters;

    @Override
    public void invoke() throws Exception {

    }

}
