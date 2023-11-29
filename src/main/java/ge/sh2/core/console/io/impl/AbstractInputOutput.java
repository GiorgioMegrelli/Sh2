package ge.sh2.core.console.io.impl;

import ge.sh2.core.console.io.InputOutput;
import ge.sh2.exception.NotImplementedException;

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
