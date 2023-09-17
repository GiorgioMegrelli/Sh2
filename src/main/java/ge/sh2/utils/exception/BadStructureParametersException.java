package ge.sh2.utils.exception;

public class BadStructureParametersException extends Sh2Exception {

    public BadStructureParametersException(Class<?> parametersCls, String reason) {
        super(String.format("Parameters '%s' has invalid structure. Reason: %s", parametersCls.getName(), reason));
    }

}
