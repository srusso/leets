package net.sr89.problems;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int islandCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (isWater(grid, i, j)) {
                    continue;
                }

                exploreIsland(grid, i, j);
                islandCount++;
            }
        }

        return islandCount;
    }

    private void exploreIsland(char[][] grid, int startI, int startJ) {
        Queue<int[]> pointsToExplore = new LinkedList<>();
        pointsToExplore.add(new int[]{startI, startJ});

        while (!pointsToExplore.isEmpty()) {
            int[] point = pointsToExplore.poll();
            int i = point[0];
            int j = point[1];

            if (isWithinBounds(grid, i, j) && !isWater(grid, i, j)) {
                grid[i][j] = '0';

                pointsToExplore.add(new int[]{i - 1, j});
                pointsToExplore.add(new int[]{i + 1, j});
                pointsToExplore.add(new int[]{i, j - 1});
                pointsToExplore.add(new int[]{i, j + 1});
            }
        }
    }

    private boolean isWithinBounds(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }

    private boolean isWater(char[][] grid, int i, int j) {
        return grid[i][j] == '0';
    }
}
