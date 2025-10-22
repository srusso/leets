package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TargetPriceTest {
    final TargetPrice solution = new TargetPrice();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int targetPrice, int[] nums) {
        assertEquals(expected, solution.findTargetSumWays(nums, targetPrice));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(5, 3, new int[]{1, 2, 3, 0, 2})
        );
    }
}