package ge.utils.exception;

public class Sh2RuntimeException extends RuntimeException {

    public Sh2RuntimeException(String message) {
        super(message);
    }

    public Sh2RuntimeException(Exception e) {
        super(e);
    }

}
