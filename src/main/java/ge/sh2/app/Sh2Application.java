package ge.sh2.app;

import ge.sh2.core.Executor;

public class Sh2Application {

    public static void run(String[] args) throws Exception {
        Sh2Options options = new Sh2Options(args);
        if(options.isCli()) {
            Sh2Cli.start();
        } else if(options.isHelp()) {
            // Print help
        } else {
            Executor.execute(args);
        }
    }

}
