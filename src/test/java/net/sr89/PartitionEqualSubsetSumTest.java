package net.sr89;

import org.jspecify.annotations.Nullable;
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

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(true, new int[]{1, 5, 11, 5}),
                Arguments.of(true, new int[]{1, 1}),
                Arguments.of(true, new int[]{3, 3, 6, 8, 16, 16, 16, 18, 20}),
                Arguments.of(true, repeat(20, 1)),
                Arguments.of(false, repeat(21, 1)),
                Arguments.of(false, new int[]{1, 1, 1}),
                Arguments.of(false, new int[]{1, 2, 3, 5})
        );
    }

    private static int[] repeat(int count, int value) {
        int[] result = new int[count];

        Arrays.fill(result, value);

        return result;
    }

}