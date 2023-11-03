package ge.sh2.utils.console;

import java.util.List;

/**
 * Source: <a href="https://gist.github.com/kamito/704813">...</a>
 * */
public class ConsoleStyle {

    public interface Describer {
        String getValue();
    }

    public enum FontColor implements Describer {
        BLACK("\033[30m"),
        RED("\033[31m"),
        GREEN("\033[32m"),
        YELLOW("\033[33m"),
        BLUE("\033[34m"),
        PURPLE("\033[35m"),
        CYAN("\033[36m"),
        WHITE("\033[37m");

        private final String value;

        FontColor(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public enum BackgroundColor implements Describer {
        BLACK("\033[40m"),
        RED("\033[41m"),
        GREEN("\033[42m"),
        YELLOW("\033[43m"),
        BLUE("\033[44m"),
        PURPLE("\033[45m"),
        CYAN("\033[46m"),
        WHITE("\033[47m");

        private final String value;

        BackgroundColor(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public enum FontType implements Describer {
        BOLD("\033[1m"),
        LOW("\033[2m"),
        ITALICS("\033[3m"),
        UNDERLINE("\033[4m"),
        RESET("\033[0m");

        private final String value;

        FontType(String value) {
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    public static String stylize(String value, List<Describer> describers) {
        Describer[] describersArr = describers.toArray(new Describer[0]);
        return stylize(value, describersArr);
    }

    public static String stylize(String value, Describer ...describers) {
        if(describers == null) {
            return value;
        }

        StringBuilder sb = new StringBuilder();
        for(Describer describer: describers) {
            if(describer != null) {
                sb.append(describer.getValue());
            }
        }
        sb.append(value)
                .append(FontType.RESET.getValue());
        return sb.toString();
    }

}
