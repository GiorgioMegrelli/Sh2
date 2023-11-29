package ge.sh2.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static ge.sh2.utils.Strings.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStrings {

    private static class StringInputSteam extends InputStream {
        private final String data;
        private int index = 0;

        public StringInputSteam(String data) {
            this.data = data;
        }

        @Override
        public int read() throws IOException {
            if(index >= data.length()) {
                throw new IOException();
            }
            int ch = (int) data.charAt(index);
            this.index++;
            return ch;
        }
    }

    @Test
    public void testReplaceAllChar() {
        assertEquals("", replaceAll("", 'a', 'b'));
        assertEquals("bbb", replaceAll("aaa", 'a', 'b'));
        assertEquals("aaa", replaceAll("aaa", 'c', 'd'));
        assertEquals("a/b/c", replaceAll("a.b.c", '.', '/'));
        assertEquals("ა-ბ-გ-დ", replaceAll("ა/ბ/გ/დ", '/', '-'));
    }

    @Test
    public void testReplaceAllString() {
        assertEquals("", replaceAll("", "b", "c"));
        assertEquals("aaa", replaceAll("", "", "aaa"));
        assertEquals("a", replaceAll("a", "b", "c"));
        assertEquals("b", replaceAll("a", "a", "b"));
        assertEquals("bbb", replaceAll("aaa", "a", "b"));
        assertEquals("bbbb", replaceAll("aaaa", "aa", "bb"));
        assertEquals("bbbbbbcc", replaceAll("aacc", "a", "bbb"));
        assertEquals("bbbbccc", replaceAll("aaaaccc", "aa", "bb"));
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

    @Test
    public void testReadStream() throws IOException {
        String testData = "test data";
        try(InputStream inputStream = new StringInputSteam(testData)) {
            String result = readStream(inputStream);

            assertEquals(testData, result);
        }
    }

}
