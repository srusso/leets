package net.sr89.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TopologicalSortTest {
    TopologicalSort sort = new TopologicalSort();

    @Test
    void sortGraph() {
        int[][] graph = {{1, 0}, {2, 6}, {1, 7}, {6, 4}, {7, 0}, {0, 5}};
        List<Integer> sorted = sort.topologicalSort(8, graph);

        assertEquals(Arrays.asList(3, 4, 5, 6, 0, 2, 7, 1), sorted);
    }
}