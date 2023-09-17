package ge.sh2.utils;

import org.junit.jupiter.api.Test;

import static ge.sh2.utils.ArrayFunctions.shift;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestArrayFunctions {

    @Test
    public void testShift() {
        Integer[] arr = new Integer[] {1, 2, 3, 4};

        arr = shift(arr, 1);
        assertEquals(3, arr.length);

        arr = shift(arr, arr.length);
        assertEquals(0, arr.length);
    }

}
