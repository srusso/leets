package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TargetSumTest {
    final TargetSum solution = new TargetSum();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int target, int[] nums) {
        assertEquals(expected, solution.findTargetSumWays(nums, target));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(5, 3, new int[]{1, 1, 1, 1, 1}),
                Arguments.of(2, 0, new int[]{0}),
                Arguments.of(256, 1, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1})
        );
    }
}