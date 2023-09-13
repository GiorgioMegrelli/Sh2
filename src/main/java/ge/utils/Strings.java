package ge.utils;

public class Strings {

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
        int index = str.indexOf(startStr);
        if(index < 0) {
            return str;
        }
        return str.substring(startStr.length());
    }

}
