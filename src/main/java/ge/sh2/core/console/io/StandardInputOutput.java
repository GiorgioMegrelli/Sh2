package ge.sh2.core.console.io;

public class StandardInputOutput extends AbstractInputOutput {

    @Override
    public void print(Object object) {
        System.out.print(asString(object));
    }

    @Override
    public void println(Object object) {
        System.out.println(asString(object));
    }

}
