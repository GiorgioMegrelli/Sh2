package ge.sh2.utils;

public class Strings {

    public static final String TAB = "    ";
    public static final String NEW_LINE = System.lineSeparator();

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isBlank(String s) {
        if(isEmpty(s)) return true;
        for(char ch: s.toCharArray()) {
            if(!Character.isWhitespace(ch)) {
                return false;
            }
        }
        return true;
    }

    public static String replaceAll(String str, char oldChar, char newChar) {
        if(oldChar == newChar) {
            return str;
        }

        StringBuilder sb = new StringBuilder(str.length());
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            sb.append((ch == oldChar)? newChar: ch);
        }
        return sb.toString();
    }

    public static String replaceStart(String str, String startStr) {
        return replaceStart(str, startStr, "");
    }

    public static String replaceStart(String str, String startStr, String newStartStr) {
        int index = str.indexOf(startStr);
        if(index < 0) {
            return str;
        }
        return newStartStr + str.substring(startStr.length());
    }

    public static String camelCaseToSnakeCase(String str) {
        return camelCaseToSnakeCase(str, '_');
    }

    public static String camelCaseToSnakeCase(String str, char sep) {
        StringBuilder builder = new StringBuilder(str.length() * 2);
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(i > 0 && Character.isUpperCase(ch)) {
                builder.append(sep);
            }
            builder.append(Character.toLowerCase(ch));
        }
        return builder.toString();
    }

}
