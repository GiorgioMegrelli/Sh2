package ge.sh2.core.console.io.impl.std;

import ge.sh2.core.console.io.InputOutputFactory;

public class StandardOutputFactory implements InputOutputFactory<StandardInputOutput> {

    private static StandardInputOutput INSTANCE = null;

    @Override
    public StandardInputOutput build() {
        if(INSTANCE == null) {
            INSTANCE = new StandardInputOutput();
        }
        return INSTANCE;
    }

}
