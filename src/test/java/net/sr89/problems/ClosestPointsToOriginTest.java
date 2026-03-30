package net.sr89.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClosestPointsToOriginTest {
    final ClosestPointsToOrigin solution = new ClosestPointsToOrigin();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int[][] expected, int[][] points, int k) {
        assertArrayEquals(expected, solution.kClosest(points, k));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[][]{{-2, 2}}, new int[][]{{1, 3}, {-2, 2}}, 1)
        );
    }

}