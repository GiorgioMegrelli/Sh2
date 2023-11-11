package ge.sh2.core.console.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestInputOutput {

    @Test
    public void testDefaultInputOutputFactory() {
        StandardOutputFactory factory = new StandardOutputFactory();

        StandardInputOutput instance0 = factory.build();
        StandardInputOutput instance1 = factory.build();

        Assertions.assertSame(instance0, instance1);
    }

    @Test
    public void testStringInputOutput() {
        StringInputOutput io = new StringInputOutput();

        io.print("Hello");
        io.println("World");

        Assertions.assertEquals("HelloWorld\n", io.toString());
    }

}
