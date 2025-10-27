package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagicDictionaryTest {
    final MagicDictionary solution = new MagicDictionary();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(Set<String> dictionary, Map<String, Boolean> searches) {
        solution.buildDict(dictionary.toArray(new String[0]));

        searches.forEach((searchTerm, expectedSearchResult) -> {
            boolean actual = solution.search(searchTerm);

            assertEquals(
                    expectedSearchResult,
                    actual,
                    () -> "Expected " + expectedSearchResult + " from searching " + searchTerm + " in dictionary: " + dictionary);
        });

    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        Set.of("hello", "leetcode"),
                        Map.of(
                                "hello", false,
                                "hhllo", true,
                                "hell", false,
                                "leetcoded", false
                        )),
                Arguments.of(
                        Set.of("hello", "hallo", "leetcode"),
                        Map.of(
                                "hello", true,
                                "hhllo", true,
                                "hell", false,
                                "leetcoded", false
                        ))
        );
    }
}