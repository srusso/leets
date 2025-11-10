package net.sr89;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/max-area-of-island/">Leetcode link</a>
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int maxIsland = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (isWater(grid, i, j)) {
                    continue;
                }

                maxIsland = Math.max(maxIsland, exploreIsland(grid, i, j));
            }
        }

        return maxIsland;
    }

    private int exploreIsland(int[][] grid, int startI, int startJ) {
        int size = 0;

        Queue<int[]> pointsToExplore = new LinkedList<>();
        pointsToExplore.add(new int[]{startI, startJ});

        while (!pointsToExplore.isEmpty()) {
            int[] point = pointsToExplore.poll();
            int i = point[0];
            int j = point[1];

            if (isWithinBounds(grid, i, j) && !isWater(grid, i, j)) {
                size++;
                grid[i][j] = 0;

                pointsToExplore.add(new int[]{i - 1, j});
                pointsToExplore.add(new int[]{i + 1, j});
                pointsToExplore.add(new int[]{i, j - 1});
                pointsToExplore.add(new int[]{i, j + 1});
            }
        }

        return size;
    }

    private boolean isWithinBounds(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    private boolean isWater(int[][] grid, int i, int j) {
        return grid[i][j] == 0;
    }
}
