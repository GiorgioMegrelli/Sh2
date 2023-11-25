package ge.sh2.command.internal;

import ge.sh2.core.console.io.impl.string.StringInputOutput;
import ge.sh2.core.command.CommandInvokable;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractCommandTest {

    protected final StringInputOutput testIO = new StringInputOutput();

    @BeforeEach
    public void postTest() {
        testIO.clear();
    }

    protected static boolean hasCommandInvokable(Class<?> cls) {
        return CommandInvokable.class.isAssignableFrom(cls);
    }

}
