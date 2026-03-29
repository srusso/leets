package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleTest {
    CourseSchedule solution = new CourseSchedule();

    @ParameterizedTest
    @MethodSource("testCases")
    void test(boolean expected, int numCourses, int[][] prerequisites) {
        assertEquals(expected, solution.canFinish(numCourses, prerequisites));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(true, 2, new int[][]{{1, 0}}),
                Arguments.of(false, 2, new int[][]{{1, 0}, {0, 1}})
        );
    }
}