package ge.sh2.utils;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSets {

    @Test
    public void testToSet() {
        final String testStr0 = "A";
        final String testStr1 = "abcde";

        assertTrue(Sets.toSet().isEmpty());

        Set<String> set = Sets.toSet(testStr0, testStr1, null);
        assertTrue(set.contains(testStr0));
        assertTrue(set.contains(testStr1));
        assertTrue(set.contains(null));
    }

}
