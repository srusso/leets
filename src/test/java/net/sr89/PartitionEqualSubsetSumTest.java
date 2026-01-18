package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
                Arguments.of(false, new int[]{1, 1, 1}),
                Arguments.of(false, new int[]{1, 2, 3, 5})
        );
    }

}