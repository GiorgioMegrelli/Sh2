package ge.sh2.utils.exception;

public class BadStructureCommandException extends Sh2Exception {

    public BadStructureCommandException(String commandName, String reason) {
        super(String.format("Command '%s' has invalid structure. Reason: %s", commandName, reason));
    }

}
