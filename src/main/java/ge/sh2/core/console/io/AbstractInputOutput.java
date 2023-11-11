package ge.sh2.core.console.io;

public abstract class AbstractInputOutput implements InputOutput {

    protected String asString(Object object) {
        if(object == null) {
            return "<null>";
        }
        return object.toString();
    }

}
