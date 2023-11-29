package ge.sh2.exception;

public class Sh2Exception extends Exception {

    public Sh2Exception(String message) {
        super(message);
    }

    public Sh2Exception(Exception e) {
        super(e);
    }

}
