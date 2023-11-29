package ge.sh2.command.custom.mkdir;

import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.command.ParametersWithArguments;

import java.io.File;

@Command(name = "mkdir", description = "Creates directories")
public class MkDir implements CommandInvokable {
    public static class MkDirParameters extends ParametersWithArguments {}

    @Parameters
    private MkDirParameters parameters;

    @Override
    public void invoke() throws Exception {
        String[] args = parameters.getArguments();
        if(args.length == 0) {
            throw new RuntimeException("No argument is provided");
        }

        File dirName = new File(args[0]);
        if(!dirName.exists()) {
            dirName.mkdirs();
        }
    }

}
