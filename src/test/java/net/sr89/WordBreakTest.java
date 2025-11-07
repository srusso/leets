package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordBreakTest {
    final WordBreak solution = new WordBreak();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(boolean expected, String s, List<String> dict) {
        assertEquals(expected, solution.wordBreak(s, dict));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(true, "leetcode", List.of("leet", "code"))
        );
    }
}