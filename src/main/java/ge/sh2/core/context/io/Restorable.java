package ge.sh2.core.context.io;

public abstract class Restorable<T> {

    public interface ContextClosure<T> {

        void run(T it) throws Exception;

    }

    protected abstract void restore();

    public abstract void use(ContextClosure<T> contextClosure) throws Exception;

}
