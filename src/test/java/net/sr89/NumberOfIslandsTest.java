package net.sr89;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NumberOfIslandsTest {
    final NumberOfIslands solution = new NumberOfIslands();

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(int expected, char[][] grid) {
        assertEquals(expected, solution.numIslands(grid));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(1, new char[][]{
                                {'1', '1', '1', '1', '0'},
                                {'1', '1', '0', '1', '0'},
                                {'1', '1', '0', '0', '0'},
                                {'0', '0', '0', '0', '0'}
                        })
        );
    }
}