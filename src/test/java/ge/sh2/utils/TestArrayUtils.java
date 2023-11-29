package ge.sh2.utils;

import org.junit.jupiter.api.Test;

import static ge.sh2.utils.ArrayUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestArrayUtils {

    @Test
    public void testShift() {
        Integer[] arr = new Integer[] {1, 2, 3, 4};

        arr = shift(arr, 1);
        assertEquals(3, arr.length);

        arr = shift(arr, arr.length);
        assertEquals(0, arr.length);
    }

    @Test
    public void testContainsT() {
        Long[] arr = new Long[] {1L, 2L, 3L};

        assertTrue(contains(arr, 1L));
        assertTrue(contains(arr, 2L));
        assertTrue(contains(arr, 3L));
        assertFalse(contains(arr, 4L));
    }

    @Test
    public void testContainsChar() {
        char[] arr = new char[] {'a', '1'};

        assertTrue(contains(arr, 'a'));
        assertTrue(contains(arr, '1'));
        assertFalse(contains(arr, '#'));
        assertFalse(contains(arr, 'b'));
    }

}
