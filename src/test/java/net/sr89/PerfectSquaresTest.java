package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PerfectSquaresTest {
    final PerfectSquares squares = new PerfectSquares();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int n) {
        assertEquals(expected, squares.numSquares(n));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(3, 12),
                Arguments.of(2, 13),
                Arguments.of(3, 4128),
                Arguments.of(4, 4703)
        );
    }
}