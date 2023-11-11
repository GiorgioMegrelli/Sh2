package ge.sh2.utils;

import org.junit.jupiter.api.Test;

import static ge.sh2.utils.Strings.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStrings {

    @Test
    public void testReplaceAll() {
        assertEquals("", replaceAll("", 'a', 'b'));
        assertEquals("bbb", replaceAll("aaa", 'a', 'b'));
        assertEquals("aaa", replaceAll("aaa", 'c', 'd'));
        assertEquals("a/b/c", replaceAll("a.b.c", '.', '/'));
        assertEquals("ა-ბ-გ-დ", replaceAll("ა/ბ/გ/დ", '/', '-'));
    }

    @Test
    public void testReplaceStart() {
        assertEquals("", replaceStart("", ""));
        assertEquals("", replaceStart("abc", "abc"));
        assertEquals("abc", replaceStart("abc", "cba"));
    }

    @Test
    public void testCamelCaseToSnakeCase() {
        assertEquals("", camelCaseToSnakeCase(""));
        assertEquals("abc", camelCaseToSnakeCase("Abc"));
        assertEquals("abc_tail", camelCaseToSnakeCase("AbcTail"));
        assertEquals("a_b_c_d_e", camelCaseToSnakeCase("ABCDE"));

        assertEquals("a%b%c%d%e", camelCaseToSnakeCase("ABCDE", '%'));
    }

    @Test
    public void testCountMatches() {
        assertEquals(4, countMatches("ABC", ""));
        assertEquals(0, countMatches("ABC", "N"));
        assertEquals(1, countMatches("ABC", "A"));
        assertEquals(4, countMatches("AAAA", "A"));
        assertEquals(3, countMatches("ABABABA", "AB"));
    }

    @Test
    public void testPadStart() {
        assertEquals("Hello", padStart("Hello", " ", 4));
        assertEquals("   ", padStart("", " ", 3));
        assertEquals("  abc", padStart("abc", " ", 5));
    }

    @Test
    public void testPadEnd() {
        assertEquals("Hello", padEnd("Hello", " ", 4));
        assertEquals("   ", padEnd("", " ", 3));
        assertEquals("abc  ", padEnd("abc", " ", 5));
    }

}
