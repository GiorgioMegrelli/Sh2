package ge.sh2.utils.builder;

import ge.sh2.utils.Strings;

public abstract class AbstractEasyStringBuilder<T> {

    private final StringBuilder sb;

    public AbstractEasyStringBuilder() {
        this(1 << 10);
    }

    public AbstractEasyStringBuilder(int capacity) {
        sb = new StringBuilder(capacity);
    }

    public T plus(char ch) {
        sb.append(ch);
        return (T) this;
    }

    public T plus(String data) {
        sb.append(data);
        return (T) this;
    }

    public T tab() {
        return plus(Strings.TAB);
    }

    public T newLine() {
        return plus(Strings.NEW_LINE);
    }

    public T many(String ...args) {
        for(String arg: args) {
            plus(arg);
        }
        return (T) this;
    }

    public T many(Object ...args) {
        for(Object arg: args) {
            plus(arg.toString());
        }
        return (T) this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
