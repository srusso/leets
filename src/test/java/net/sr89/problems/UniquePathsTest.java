package net.sr89.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsTest {
    final UniquePaths solution = new UniquePaths();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int m, int n) {
        assertEquals(expected, solution.uniquePaths(m, n));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(3, 3, 2)
        );
    }

}