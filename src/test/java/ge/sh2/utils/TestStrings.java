package ge.sh2.utils;

import org.junit.jupiter.api.Test;

import static ge.sh2.utils.Strings.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStrings {

    @Test
    public void testReplaceAll() {
        assertEquals(replaceAll("", 'a', 'b'), "");
        assertEquals(replaceAll("aaa", 'a', 'b'), "bbb");
        assertEquals(replaceAll("aaa", 'c', 'd'), "aaa");
        assertEquals(replaceAll("a.b.c", '.', '/'), "a/b/c");
        assertEquals(replaceAll("ა/ბ/გ/დ", '/', '-'), "ა-ბ-გ-დ");
    }

    @Test
    public void testReplaceStart() {
        assertEquals(replaceStart("", ""), "");
        assertEquals(replaceStart("abc", "abc"), "");
        assertEquals(replaceStart("abc", "cba"), "abc");
    }

    @Test
    public void testCamelCaseToSnakeCase() {
        assertEquals(camelCaseToSnakeCase(""), "");
        assertEquals(camelCaseToSnakeCase("Abc"), "abc");
        assertEquals(camelCaseToSnakeCase("AbcTail"), "abc_tail");
        assertEquals(camelCaseToSnakeCase("ABCDE"), "a_b_c_d_e");

        assertEquals(camelCaseToSnakeCase("ABCDE", '%'), "a%b%c%d%e");
    }

}
