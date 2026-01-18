package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PartitionEqualSubsetSumTest {
    final PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(boolean expected, int[] nums) {
        assertEquals(expected, solution.canPartition(nums));
    }

    @ParameterizedTest
    @MethodSource("slowTestCases")
    void runSlowTests(boolean expected, int[] nums) {
        assertEquals(expected, solution.canPartition(nums));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(true, new int[]{1, 5, 11, 5}),
                Arguments.of(true, new int[]{1, 1}),
                Arguments.of(true, new int[]{14, 9, 8, 4, 3, 2}),

                // 3 6 8 16 20
                // 3 16 16 18
                Arguments.of(true, new int[]{3, 3, 6, 8, 16, 16, 16, 18, 20}),
                Arguments.of(false, new int[]{1, 1, 1}),
                Arguments.of(false, new int[]{1, 2, 3, 5})
        );
    }

    private static Stream<Arguments> slowTestCases() {
        return Stream.of(
                Arguments.of(true, repeat(200, 1)),
                Arguments.of(false, repeat(201, 1)),
                Arguments.of(true, new int[]{41, 20, 99, 98, 50, 48, 64, 15, 74, 94, 60, 33, 61, 34, 47, 35, 24, 58, 28, 73, 36, 51, 80, 57, 42, 52, 73, 27, 94, 59, 50, 99, 32, 65, 76, 62, 69, 80, 41, 51, 49, 74, 93, 12, 77, 30, 25, 59, 55, 13, 41, 23, 34, 31, 47, 53, 8, 88, 86, 88, 36, 32, 23, 37, 1, 7, 67, 49, 20, 31, 59, 99, 15, 21, 47, 35, 93, 1, 14, 56, 57, 36, 13, 27, 26, 64, 63, 52, 98, 20, 52, 23, 84, 39, 34, 59, 98, 71, 90, 9}),
                Arguments.of(true, new int[]{5, 79, 2, 4, 8, 16, 32, 64, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100})
        );
    }

    private static int[] repeat(int count, int value) {
        int[] result = new int[count];

        Arrays.fill(result, value);

        return result;
    }

}