package ge.sh2.core.context.io;

import ge.sh2.core.console.io.InputOutput;
import ge.sh2.core.console.io.impl.err.StandardErrorInputOutputFactory;
import ge.sh2.core.console.io.impl.std.StandardOutputFactory;

public class StandardSh2ContextIO extends Sh2ContextIO {

    private static final InputOutput OUT_IO_INSTANCE_DEFAULT;
    private static final InputOutput ERR_IO_INSTANCE_DEFAULT;

    static {
        OUT_IO_INSTANCE_DEFAULT = new StandardOutputFactory().build();
        ERR_IO_INSTANCE_DEFAULT = new StandardErrorInputOutputFactory().build();
    }

    public StandardSh2ContextIO() {
        super(OUT_IO_INSTANCE_DEFAULT, ERR_IO_INSTANCE_DEFAULT);
    }

}
