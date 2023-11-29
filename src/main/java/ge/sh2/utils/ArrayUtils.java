package ge.sh2.utils;

import java.util.Arrays;
import java.util.Objects;

public class ArrayUtils {

    public static boolean contains(char[] chars, char elem) {
        Objects.requireNonNull(chars);
        for(char c : chars) {
            if (c == elem) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean contains(T[] arr, T elem) {
        Objects.requireNonNull(arr);
        for(T e : arr) {
            if (e.equals(elem)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] shift(T[] arr) {
        return shift(arr, 1);
    }

    public static <T> T[] shift(T[] arr, int n) {
        if(n < 0) {
            throw new RuntimeException("Invalid shift size: " + n);
        }
        if(arr.length < n) {
            throw new RuntimeException("Invalid array length: " + arr.length);
        }
        return Arrays.copyOfRange(arr, n, arr.length);
    }

}
