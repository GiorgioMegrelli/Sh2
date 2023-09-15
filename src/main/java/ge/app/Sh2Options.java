package ge.app;

public class Sh2Options {

    public static final String OPT_CLI = "cli";
    public static final String OPT_HELP = "help";

    public Sh2Options(String[] args) {}

    public boolean isCli() {
        return false;
    }

    public boolean isHelp() {
        return false;
    }

}
