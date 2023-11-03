package ge.sh2.utils.exception;

public class UnknownCommand extends Sh2Exception {

    public UnknownCommand(String commandName) {
        super("Unknown command: " + commandName);
    }

}
