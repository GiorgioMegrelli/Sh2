package ge.sh2.core.console.io;

public class SlowStandardInputOutput extends AbstractInputOutput {

    private final StringBuilder collector = new StringBuilder();
    private final InputOutput io = new StandardOutputFactory().build();

    @Override
    public void print(Object object) {
        String s = asString(object);
        int index = s.lastIndexOf('\n');
        if(index < 0) {
            collector.append(s);
        } else {
            io.print(collector);
            io.print(s.substring(0, index + 1));
            clearCollector();
            collector.append(s.substring(index + 1));
        }
    }

    @Override
    public void println(Object object) {
        collector.append(asString(object));
        io.println(collector.toString());
        clearCollector();
    }

    private void clearCollector() {
        collector.setLength(0);
    }

}
