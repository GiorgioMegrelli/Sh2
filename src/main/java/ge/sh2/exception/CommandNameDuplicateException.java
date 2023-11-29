package ge.sh2.exception;

public class CommandNameDuplicateException extends Sh2RuntimeException {

    public CommandNameDuplicateException(String name) {
        super("Duplicated name: " + name);
    }

}
