package ge.app;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Sh2Options {

    public static final String OPT_CLI = "cli";
    public static final String OPT_HELP = "help";
    public static final Options OPTS_INSTANCE;

    static {
        OPTS_INSTANCE = new Options();
        OPTS_INSTANCE.addOption(OPT_CLI, false, "Use CLI Mode");
        OPTS_INSTANCE.addOption(OPT_HELP, false, "Show help message");
    }

    private final CommandLine commandLine;

    public Sh2Options(String[] args) throws ParseException {
        commandLine = new DefaultParser().parse(OPTS_INSTANCE, args);
    }

    public boolean isCli() {
        return commandLine.hasOption(OPT_CLI);
    }

    public boolean isHelp() {
        return commandLine.hasOption(OPT_HELP);
    }

}
