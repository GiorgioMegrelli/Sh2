package ge.sh2.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
        return replaceAll(str, String.valueOf(oldChar), String.valueOf(newChar));
    }

    public static String replaceAll(String str, String oldStr, String newStr) {
        if(str.isEmpty()) {
            if(oldStr.isEmpty()) {
                return newStr;
            }
            return str;
        } else if(oldStr.equals(newStr)) {
            return str;
        }

        double scale = 1.0 * newStr.length() / oldStr.length();
        int newSize = (int) Math.round(scale * str.length());
        StringBuilder sb = new StringBuilder(newSize);
        int prev = 0;
        while(true) {
            int ind = str.indexOf(oldStr, prev);
            if(ind < 0) {
                sb.append(str.substring(prev));
                break;
            }
            sb.append(str, prev, ind);
            sb.append(newStr);
            prev = ind + oldStr.length();
        }
        return sb.toString();
    }

    public static String replaceStart(String str, String startStr) {
        return replaceStart(str, startStr, "");
    }

    public static String replaceStart(String str, String startStr, String newStartStr) {
        int index = str.indexOf(startStr);
        if(index != 0) {
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

    public static int countMatches(String str, String sub) {
        if(sub.isEmpty()) {
            return str.length() + 1;
        }

        int count = 0;
        int lastIndex = 0;
        while(true) {
            lastIndex = str.indexOf(sub, lastIndex);
            if(lastIndex < 0) {
                break;
            }
            count++;
            lastIndex++;
        }
        return count;
    }

    public static String padStart(String str, String subStr, int maxLength) {
        if(str.length() >= maxLength) {
            return str;
        }
        return subStr.repeat(maxLength - str.length()) + str;
    }

    public static String padEnd(String str, String subStr, int maxLength) {
        if(str.length() >= maxLength) {
            return str;
        }
        return str + subStr.repeat(maxLength - str.length());
    }

    public static String capitalize(String str) {
        if(str.isEmpty()) {
            return str;
        }
        if(str.length() == 1) {
            return str.toUpperCase();
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String readStream(InputStream stream) throws IOException {
        return readStreamAsBytes(stream).toString(StandardCharsets.UTF_8);
    }

    public static ByteArrayOutputStream readStreamAsBytes(InputStream stream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        while(true) {
            int len = stream.read(buffer);
            result.write(buffer, 0, len);
            if(len < buffer.length) {
                break;
            }
        }
        return result;
    }

}
