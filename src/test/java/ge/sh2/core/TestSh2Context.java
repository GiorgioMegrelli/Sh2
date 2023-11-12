package ge.sh2.core;

import ge.sh2.core.console.io.impl.std.StandardInputOutput;
import ge.sh2.core.console.io.impl.string.StringInputOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSh2Context {

    private static class TestInputOutput extends StringInputOutput {}

    @Test
    public void testRestore() {
        Assertions.assertEquals(StandardInputOutput.class, Sh2Context.getIO().getClass());

        Sh2Context.setIO(new TestInputOutput());

        Assertions.assertEquals(TestInputOutput.class, Sh2Context.getIO().getClass());

        Sh2Context.restore();

        Assertions.assertEquals(StandardInputOutput.class, Sh2Context.getIO().getClass());
    }

}
