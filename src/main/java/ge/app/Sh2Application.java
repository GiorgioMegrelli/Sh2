package ge.app;

import ge.core.Executor;

public class Sh2Application {

    public static void run(String[] args) throws Exception {
        Sh2Options options = new Sh2Options(args);
        if(options.isCli()) {
            Sh2Cli.start();
        } else if(options.isHelp()) {
            // Print help
        } else {
            new Executor().execute(args);
        }
    }

}
