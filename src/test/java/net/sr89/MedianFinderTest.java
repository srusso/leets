package net.sr89;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianFinderTest {
    @Test
    void testOddNumberOfNumbers() {
        MedianFinder solution = new MedianFinder();
        solution.addNum(1);
        assertEquals(1.0d, solution.findMedian());
        solution.addNum(2);
        assertEquals(1.5d, solution.findMedian());
        solution.addNum(3);
        assertEquals(2.0d, solution.findMedian());
    }

    @Test
    void testEvenNumberOfNumbers() {
        MedianFinder solution = new MedianFinder();
        solution.addNum(1);
        solution.addNum(2);
        assertEquals(1.5d, solution.findMedian());
    }

    @Test
    void testZeros() {
        MedianFinder solution = new MedianFinder();
        solution.addNum(0);
        solution.addNum(0);
        assertEquals(0d, solution.findMedian());
    }

    @Test
    void testWithSomeMoreNumbers() {
        MedianFinder solution = new MedianFinder();
        Arrays.stream(new int[]{5, 3, 2, -4, 7, 1, 1})
                .forEach(solution::addNum);
        // add smaller
        // -4 2 3m 5 7  odd
        // -4 1 2m 3m 5 7  even, went back
        // -4 1 1 2m 3 5 7  odd, did not move

        // add bigger
        // -4 2 3m 5 7  odd
        // -4 2 3m 5m 5 7  even, did not move
        // -4 2 3 5m 5 5 7  odd, went forward

        assertEquals(2, solution.findMedian());
    }

    @Test
    void testWithSomeMoreNumbers_even() {
        MedianFinder solution = new MedianFinder();
        Arrays.stream(new int[]{5, 3, 2, -4, 7, 1})
                .forEach(solution::addNum);

        assertEquals(2.5, solution.findMedian(), 0.001D);
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void parameterizedTests(List<String> commands, List<Integer> arguments, List<Double> expected) {
        MedianFinder solution = new MedianFinder();

        final int size = commands.size();

        if (arguments.size() != size) {
            throw new RuntimeException("Size differs");
        }

        int totalAdded = 0;

        for (int i = 0; i < size; i++) {
            final var command = commands.get(i);
            final var argument = arguments.get(i);

            if (command.equals("addNum")) {
                solution.addNum(argument);
                totalAdded++;
                System.out.println("Added " + argument);
            } else if (command.equals("findMedian")) {
                final var expectedMedian = expected.get(i);
                final var median = solution.findMedian();
                System.out.println("After adding " + totalAdded + " numbers. Expected: " + expectedMedian + ", actual: " + median);
                assertEquals(expectedMedian, median, 0.001D);
            } else {
                throw new IllegalArgumentException("Unrecognized command " + command);
            }
        }
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"
                        ),
                        List.of(
                                12, 0, 10, 0, 13, 0, 11, 0, 5, 0, 15, 0, 1, 0, 11, 0, 6, 0, 17, 0, 14, 0, 8, 0, 17, 0, 6, 0, 4, 0, 16, 0, 8, 0, 10, 0, 2, 0, 12, 0, 0, 0
                        ),
                        List.of(
                                0, 12.00000, 0, 11.00000, 0, 12.00000, 0, 11.50000, 0, 11.00000, 0, 11.50000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 11.00000, 0, 10.50000, 0, 10.00000, 0, 10.50000, 0, 10.00000
                        )
                )

        );
    }
}