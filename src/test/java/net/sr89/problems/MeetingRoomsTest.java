package net.sr89.problems;

import net.sr89.types.Interval;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeetingRoomsTest {
    MeetingRooms solution = new MeetingRooms();

    @ParameterizedTest
    @MethodSource("testCases")
    void test(boolean expected, List<Interval> meetings) {
        assertEquals(expected, solution.canAttendMeetings(meetings));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(false, Arrays.asList(new Interval(0, 30), new Interval(5, 10), new Interval(15, 20))),
                Arguments.of(true, Arrays.asList(new Interval(5, 8), new Interval(9, 15)))
        );
    }
}