package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecodeWaysTest {

    DecodeWays ways = new DecodeWays();

    @ParameterizedTest
    @MethodSource("testCases")
    void decodeWays(int expected, String s) {
        assertEquals(expected, ways.numDecodings(s));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(2, "12"),
                Arguments.of(0, "02"),
                Arguments.of(3, "226"),
                Arguments.of(1, "1"),
                Arguments.of(0, "0"),
                Arguments.of(1, "10"),
                Arguments.of(2, "26"),
                Arguments.of(5, "2226"),
                Arguments.of(7, "22226"),
                Arguments.of(1, "2101"),
                Arguments.of(1, "99")
        );
    }
}