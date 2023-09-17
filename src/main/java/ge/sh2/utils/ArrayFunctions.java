package ge.sh2.utils;

import java.util.Arrays;

public class ArrayFunctions {

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
