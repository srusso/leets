package net.sr89.problems;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CourseSchedule3Test {
    CourseSchedule3 solution = new CourseSchedule3();

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int expected, int[][] courses) {
        assertEquals(expected, solution.scheduleCourse(courses));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(3, new int[][]{{100,200},{200,1300},{1000,1250},{2000,3200}})
        );
    }
}