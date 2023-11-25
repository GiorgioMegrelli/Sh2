package ge.sh2.core.context.io;

import ge.sh2.core.console.io.InputOutput;

public abstract class Sh2ContextIO extends Restorable<Sh2ContextIO.ModifiableSh2ContextIO> implements IUnmodifiableSh2ContextIO {

    public static class ModifiableSh2ContextIO implements IModifiableSh2ContextIO {
        private final Sh2ContextIO contextIO;

        public ModifiableSh2ContextIO(Sh2ContextIO contextIO) {
            this.contextIO = contextIO;
        }

        @Override
        public InputOutput out() {
            return contextIO.out();
        }

        @Override
        public void setOut(InputOutput out) {
            contextIO.outCurr = out;
        }

        @Override
        public InputOutput err() {
            return contextIO.err();
        }

        @Override
        public void setErr(InputOutput err) {
            contextIO.errCurr = err;
        }
    }

    private final InputOutput outInitial;
    private final InputOutput errInitial;
    private InputOutput outCurr;
    private InputOutput errCurr;

    public Sh2ContextIO(InputOutput out, InputOutput err) {
        outInitial = out;
        errInitial = err;
        restore();
    }

    @Override
    public InputOutput out() {
        return outCurr;
    }

    @Override
    public InputOutput err() {
        return errCurr;
    }

    @Override
    protected void restore() {
        outCurr = outInitial;
        errCurr = errInitial;
    }

    @Override
    public void use(ContextClosure<ModifiableSh2ContextIO> contextClosure) throws Exception {
        try {
            contextClosure.run(new ModifiableSh2ContextIO(this));
        } finally {
            restore();
        }
    }

}
