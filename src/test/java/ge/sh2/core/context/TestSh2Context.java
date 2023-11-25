package ge.sh2.core.context;

import ge.sh2.core.console.io.InputOutput;
import ge.sh2.core.console.io.impl.err.StandardErrorInputOutput;
import ge.sh2.core.console.io.impl.std.StandardInputOutput;
import ge.sh2.core.console.io.impl.string.StringInputOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSh2Context {

    private static class TestInputOutput extends StringInputOutput {}

    @Test
    public void testIO() throws Exception {
        String outMessage = "Good Message";
        String errMessage = "Bad Message";
        InputOutput out = new StringInputOutput();
        InputOutput err = new StringInputOutput();

        Sh2Context.IO().use((io) -> {
            io.setOut(out);
            io.setErr(err);

            io.out().print(outMessage);
            io.err().print(errMessage);

            Assertions.assertEquals(outMessage, out.toString());
            Assertions.assertEquals(errMessage, err.toString());
        });
    }

    @Test
    public void testRestore() throws Exception {
        Assertions.assertEquals(StandardInputOutput.class, Sh2Context.IO().out().getClass());
        Assertions.assertEquals(StandardErrorInputOutput.class, Sh2Context.IO().err().getClass());

        Sh2Context.IO().use((io) -> {
            io.setOut(new TestInputOutput());
            io.setErr(new TestInputOutput());

            Assertions.assertEquals(TestInputOutput.class, Sh2Context.IO().out().getClass());
            Assertions.assertEquals(TestInputOutput.class, Sh2Context.IO().err().getClass());
        });

        Assertions.assertEquals(StandardInputOutput.class, Sh2Context.IO().out().getClass());
        Assertions.assertEquals(StandardErrorInputOutput.class, Sh2Context.IO().err().getClass());
    }

}
