package ge.sh2.command.internal;

import ge.sh2.core.Sh2Context;
import ge.sh2.core.command.CommandInvokable;
import ge.sh2.core.console.io.InputOutput;
import ge.sh2.core.console.io.StringInputOutput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractCommandTest {

    @BeforeEach
    public void preTest() {
        Sh2Context.setIO(new StringInputOutput());
    }

    @AfterEach
    public void postTest() {
        Sh2Context.restore();
    }

    protected InputOutput getTestIO() {
        return Sh2Context.getIO();
    }

    protected static boolean hasCommandInvokable(Class<?> cls) {
        return CommandInvokable.class.isAssignableFrom(cls);
    }

}
