package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniquePaths3WithObstaclesTest {
    final UniquePaths3WithObstacles solution = new UniquePaths3WithObstacles();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int[][] grid) {
        assertEquals(expected, solution.uniquePathsIII(grid));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        2,
                        new int[][]{
                                {1, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 2, -1}
                        }
                ),
                Arguments.of(
                        4,
                        new int[][]{
                                {1, 0, 0, 0},
                                {0, 0, 0, 0},
                                {0, 0, 0, 2}
                        }
                ),
                Arguments.of(
                        0,
                        new int[][]{
                                {0, 1}, {2, 0}
                        }
                )
        );
    }
}