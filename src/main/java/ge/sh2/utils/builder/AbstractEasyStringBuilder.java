package ge.sh2.utils.builder;

import ge.sh2.utils.Strings;

import java.security.InvalidParameterException;
import java.util.Objects;

public abstract class AbstractEasyStringBuilder<T> {

    private final StringBuilder sb;

    public AbstractEasyStringBuilder() {
        this(1 << 10);
    }

    public AbstractEasyStringBuilder(int capacity) {
        sb = new StringBuilder(capacity);
    }

    private T castedThis() {
        return (T) this;
    }

    public T load(T other) {
        Objects.requireNonNull(other);
        return plus(other.toString());
    }

    public T plus(char ch) {
        sb.append(ch);
        return castedThis();
    }

    public T plus(String data) {
        Objects.requireNonNull(data);
        sb.append(data);
        return castedThis();
    }

    public T plus(Object data) {
        Objects.requireNonNull(data);
        return plus(data.toString());
    }

    public T tab() {
        return tab(1);
    }

    public T tab(int count) {
        if(count < 0) {
            throw new InvalidParameterException("Tab count: " + count);
        }
        for(int i = 0; i < count; i++) {
            plus(Strings.TAB);
        }
        return castedThis();
    }

    public T newLine() {
        return plus(Strings.NEW_LINE);
    }

    public T many(String ...args) {
        for(String arg: args) {
            plus(arg);
        }
        return castedThis();
    }

    public T many(Object ...args) {
        for(Object arg: args) {
            plus(arg);
        }
        return castedThis();
    }

    @Override
    public String toString() {
        return sb.toString();
    }

}
