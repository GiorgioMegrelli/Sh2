package ge.sh2.utils.builder;

import ge.sh2.core.console.style.ConsoleStyle;

public class StyledEasyStringBuilder extends AbstractEasyStringBuilder<StyledEasyStringBuilder> {

    public StyledEasyStringBuilder(int capacity) {
        super(capacity);
    }

    public StyledEasyStringBuilder plus(String data, ConsoleStyle.Describer ...describers) {
        return plus(ConsoleStyle.stylize(data, describers));
    }

}
