package net.sr89.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRooms3Test {
    MeetingRooms3 solution = new MeetingRooms3();

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int expected, int n, int[][] meetings) {
        assertEquals(expected, solution.mostBooked(n, meetings));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(0, 2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}),
                Arguments.of(1, 3, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}})
        );
    }
}