package ge.sh2.core.console.io;

import ge.sh2.utils.exception.NotImplementedException;
import jdk.jshell.spi.ExecutionControl;

public abstract class AbstractInputOutput implements InputOutput {

    protected String asString(Object object) {
        if(object == null) {
            return "<null>";
        }
        return object.toString();
    }

    @Override
    public void print(Object object) {
        throw new NotImplementedException();
    }

    @Override
    public void println(Object object) {
        throw new NotImplementedException();
    }

    @Override
    public void println() {
        println("");
    }

}
