package net.sr89.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsWithObstaclesTest {
    final UniquePathsWithObstacles solution = new UniquePathsWithObstacles();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int[][] grid) {
        assertEquals(expected, solution.uniquePathsWithObstacles(grid));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        2,
                        new int[][]{
                                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
                        }
                ),
                Arguments.of(
                        0,
                        new int[][]{
                                {1}, {0}
                        }
                )
        );
    }
}