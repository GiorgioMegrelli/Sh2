package ge.sh2.command.custom.touch;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.annotation.Command;
import ge.sh2.core.annotation.Parameters;

import java.io.File;

@Command
public class Touch implements CommandInvokable {

    @Parameters
    private TouchParameters parameters;

    @Override
    public void invoke() throws Exception {
        String[] arguments = parameters.getArguments();
        if(arguments == null || arguments.length == 0) {
            Sh2Context.getIO().println("No Arguments");
            return;
        }
        for(String arg: arguments) {
            File file = new File(arg);
            if(!file.createNewFile()) {
                Sh2Context.getIO().println("File already exists: " + arg);
            }
        }
    }

}
