package ge.utils;

import org.junit.jupiter.api.Test;

import static ge.utils.Strings.replaceAll;
import static ge.utils.Strings.replaceStart;
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

}
