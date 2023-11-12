package ge.sh2.core.console.io.impl.std;

import ge.sh2.core.console.io.InputOutputFactory;

public class SlowStandardInputOutputFactory implements InputOutputFactory<SlowStandardInputOutput> {

    private static SlowStandardInputOutput INSTANCE = null;

    @Override
    public SlowStandardInputOutput build() {
        if(INSTANCE == null) {
            INSTANCE = new SlowStandardInputOutput();
        }
        return INSTANCE;
    }

}
