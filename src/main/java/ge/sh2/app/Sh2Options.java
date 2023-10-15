package ge.sh2.app;

public class Sh2Options {

    public static final String OPT_CLI = "cli";
    public static final String OPT_HELP = "help";
    private final String[] args;

    public Sh2Options(String[] args) {
        this.args = args;
    }

    public boolean isCli() {
        return false;
    }

    public boolean isHelp() {
        return OPT_HELP.equalsIgnoreCase(args[0]);
    }

}
