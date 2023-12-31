package ge.sh2.exception;

public class BadStructureCommandException extends Sh2RuntimeException {

    public BadStructureCommandException(String commandName, String reason) {
        super(String.format("Command '%s' has invalid structure. Reason: %s", commandName, reason));
    }

}
