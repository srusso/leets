package net.sr89;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianFinderTest {
    MedianFinder solution = new MedianFinder();

    @Test
    void test1() {
        MedianFinder solution = new MedianFinder();
        solution.addNum(1);
        solution.addNum(2);
        assertEquals(1.5d, solution.findMedian());
        solution.addNum(3);
        assertEquals(2.0d, solution.findMedian());
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void runTests(List<String> ops, List<Integer> nums, List<Double> expected) {

    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        List.of("addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"),
                        List.of(6, null, 10, null, 2, null, 6, null, 5, null, 0, null, 6, null, 3, null, 1, null, 0, null, 0, null),
                        List.of(null, null, 6.00000, null, 8.00000, null, 6.00000, null, 6.00000, null, 6.00000, null, 5.50000, null, 6.00000, null, 5.50000, null, 5.00000, null, 4.00000, null, 3.00000)
                )
        );
    }
}