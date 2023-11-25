package ge.sh2.core.context.io;

import ge.sh2.core.console.io.InputOutput;

public interface IModifiableSh2ContextIO extends IUnmodifiableSh2ContextIO {

    void setOut(InputOutput out);

    void setErr(InputOutput err);

}
