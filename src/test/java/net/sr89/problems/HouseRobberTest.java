package net.sr89.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseRobberTest {
    final HouseRobber robber = new HouseRobber();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, int[] nums) {
        assertEquals(expected, robber.rob(nums));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(1, new int[]{1}),
                Arguments.of(2, new int[]{1, 2}),
                Arguments.of(2, new int[]{2, 2}),
                Arguments.of(2, new int[]{2, 1}),
                Arguments.of(6, new int[]{2, 1, 4}),
                Arguments.of(9, new int[]{2, 9, 4}),
                Arguments.of(10, new int[]{2, 1, 4, 8}),
                Arguments.of(6, new int[]{2, 1, 4, 1}),
                Arguments.of(12, new int[]{2, 7, 9, 3, 1}),
                Arguments.of(4, new int[]{1, 2, 3, 1})
        );
    }
}