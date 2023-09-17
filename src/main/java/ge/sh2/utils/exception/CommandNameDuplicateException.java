package ge.sh2.utils.exception;

public class CommandNameDuplicateException extends Sh2Exception {

    public CommandNameDuplicateException(String name) {
        super("Duplicated name: " + name);
    }

}
