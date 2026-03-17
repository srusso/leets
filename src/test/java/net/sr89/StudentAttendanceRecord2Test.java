package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StudentAttendanceRecord2Test {
    StudentAttendanceRecord2 solution = new StudentAttendanceRecord2();

    @ParameterizedTest
    @MethodSource("testCases")
    void decodeWays(int expected, int n) {
        assertEquals(expected, solution.checkRecord(n));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(3, 1),
                Arguments.of(8, 2),
                Arguments.of(19, 3),
                Arguments.of(43, 4),
                Arguments.of(94, 5),
                Arguments.of(200, 6),
                Arguments.of(183236316, 10101)
        );
    }

}