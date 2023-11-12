package ge.sh2.core.console.io.impl.err;

import ge.sh2.core.console.io.InputOutputFactory;

public class StandardErrorInputOutputFactory implements InputOutputFactory<StandardErrorInputOutput> {

    private static StandardErrorInputOutput INSTANCE = null;

    @Override
    public StandardErrorInputOutput build() {
        if(INSTANCE == null) {
            INSTANCE = new StandardErrorInputOutput();
        }
        return INSTANCE;
    }

}
