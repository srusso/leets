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
                // trivial positive case
                Arguments.of(true, 2, new int[][]{{1, 0}}),

                // trivial cycle
                Arguments.of(false, 2, new int[][]{{1, 0}, {0, 1}}),

                // test case: node has two "parents", but no cycle
                Arguments.of(true, 5, new int[][]{{1, 4}, {2, 4}, {3, 1}, {3, 2}}),

                Arguments.of(true, 8, new int[][]{{1,0},{2,6},{1,7},{6,4},{7,0},{0,5}})
        );
    }
}