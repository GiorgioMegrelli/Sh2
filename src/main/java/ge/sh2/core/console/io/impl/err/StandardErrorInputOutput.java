package ge.sh2.core.console.io.impl.err;

import ge.sh2.core.console.io.impl.AbstractInputOutput;

public class StandardErrorInputOutput extends AbstractInputOutput {

    @Override
    public void print(Object object) {
        System.err.print(asString(object));
    }

    @Override
    public void println(Object object) {
        System.err.println(asString(object));
    }

}
