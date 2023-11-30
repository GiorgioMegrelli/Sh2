package ge.sh2.parser;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isWhitespace;

public class CmdParserUtils {

    public static List<String> parseLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line.length());
        int quote = -1;
        for(int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if(quote > 0) {
                if(isQuote(ch) && quote == ch) {
                    quote = -1;
                } else {
                    if(ch == '\\' && i + 1 < line.length()) {
                        sb.append(escapeChar(line.charAt(i + 1)));
                        i++;
                    } else {
                        sb.append(ch);
                    }
                }
            } else {
                if(isQuote(ch)) {
                    quote = ch;
                } else if(isWhitespace(ch)) {
                    cutValue(sb, result);
                } else {
                    sb.append(ch);
                }
            }
        }
        cutValue(sb, result);
        return result;
    }

    private static void cutValue(StringBuilder sb, List<String> result) {
        if(!sb.isEmpty()) {
            result.add(sb.toString());
            sb.setLength(0);
        }
    }

    private static boolean isQuote(char ch) {
        return ch == '\'' || ch == '"';
    }

    private static char escapeChar(char ch) {
        return switch (ch) {
            case 't' -> '\t';
            case 'b' -> '\b';
            case 'n' -> '\n';
            case 'r' -> '\r';
            case 'f' -> '\f';
            default -> ch;
        };
    }

}
