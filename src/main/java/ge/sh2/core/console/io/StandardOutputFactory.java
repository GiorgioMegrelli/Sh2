package ge.sh2.core.console.io;

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
