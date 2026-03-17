package net.sr89;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static net.sr89.StudentAttendanceRecord2.calcModulo;
import static net.sr89.StudentAttendanceRecord2.positive;
import static org.junit.jupiter.api.Assertions.*;

class StudentAttendanceRecord2Test {
    StudentAttendanceRecord2 solution = new StudentAttendanceRecord2();

    @ParameterizedTest
    @MethodSource("testCases")
    void decodeWays(int expected, int n) {
        assertEquals(expected, solution.checkRecord(n));
    }

    @Test
    void testPositiveModulo() {
        assertEquals(5, positive(-1000000007L * 3 + 5));
        assertEquals(0, positive(-1000000007L));
        assertEquals(1000000007 - 6, positive(-6));
    }

    @Test
    void testModulo() {
        assertEquals(0, calcModulo(0));
        assertEquals(5, calcModulo(5));
        assertEquals(0, calcModulo(1000000007));
        assertEquals(0, calcModulo(1000000007 * 2));
        assertEquals(0, calcModulo(1000000007L * 4));
        assertEquals(2, calcModulo(1000000007L * 4 + 2));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(3, 1),
                Arguments.of(8, 2),
                Arguments.of(19, 3),
                Arguments.of(43, 4),
                Arguments.of(94, 5),
                Arguments.of(200, 6),
                Arguments.of(985598218, 100),
                Arguments.of(183236316, 10101)
        );
    }

}