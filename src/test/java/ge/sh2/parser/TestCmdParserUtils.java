package ge.sh2.parser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static ge.sh2.parser.CmdParserUtils.parseLine;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdParserUtils {

    private record TestCase(String input, List<String> output) {
        public static TestCase of(String input, List<String> output) {
            return new TestCase(input, output);
        }
    }

    private static final List<TestCase> testCases;

    static {
        testCases = asList(
                TestCase.of(
                        "single-input",
                        singletonList("single-input")
                ),
                TestCase.of(
                        "test run a b c",
                        asList("test", "run", "a", "b", "c")
                ),
                TestCase.of(
                        "'test run a b c'",
                        singletonList("test run a b c")
                ),
                TestCase.of(
                        "test --arg='aaa'",
                        asList("test", "--arg=aaa")
                ),
                TestCase.of(
                        "test --arg 'aaa'",
                        asList("test", "--arg", "aaa")
                ),
                TestCase.of(
                        "test --arg 'a a'",
                        asList("test", "--arg", "a a")
                ),
                TestCase.of(
                        "test 'a \\' a'",
                        asList("test", "a ' a")
                ),
                TestCase.of(
                        "\"a ' a\"",
                        singletonList("a ' a")
                )
        );
    }

    @Test
    public void testParseSimple() {
        for(int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            assertEquals(testCase.output, parseLine(testCase.input), "Index: " + i);
        }
    }

}
