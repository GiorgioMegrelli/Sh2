package ge.sh2.core;

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

        Sh2Context.use(() -> {
            Sh2Context.setIO(out);
            Sh2Context.setErrIO(err);

            Sh2Context.getIO().print(outMessage);
            Sh2Context.getErrIO().print(errMessage);

            Assertions.assertEquals(outMessage, out.toString());
            Assertions.assertEquals(errMessage, err.toString());
        });
    }

    @Test
    public void testRestore() {
        Assertions.assertEquals(StandardInputOutput.class, Sh2Context.getIO().getClass());
        Assertions.assertEquals(StandardErrorInputOutput.class, Sh2Context.getErrIO().getClass());

        Sh2Context.setIO(new TestInputOutput());
        Sh2Context.setErrIO(new TestInputOutput());

        Assertions.assertEquals(TestInputOutput.class, Sh2Context.getIO().getClass());
        Assertions.assertEquals(TestInputOutput.class, Sh2Context.getErrIO().getClass());

        Sh2Context.restore();

        Assertions.assertEquals(StandardInputOutput.class, Sh2Context.getIO().getClass());
        Assertions.assertEquals(StandardErrorInputOutput.class, Sh2Context.getErrIO().getClass());
    }

}
