package ge.sh2.core.console.io;

public class StringInputOutput extends AbstractInputOutput {

    private final StringBuilder sb = new StringBuilder(1 << 7);

    @Override
    public void print(Object object) {
        sb.append(asString(object));
    }

    @Override
    public void println(Object object) {
        sb.append(asString(object)).append('\n');
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
