package net.sr89;

import java.util.Arrays;

public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] grid) {
        initGrid(grid);

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
                } else {
                    grid[i][j] = 0;
                }
            }
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }

    private static void initGrid(int[][] grid) {
        boolean foundObstacle = false;

        for (int i = 0; i < grid[0].length; i++) {
            if (foundObstacle) {
                grid[0][i] = 0;
            } else if (grid[0][i] == 1) {
                grid[0][i] = 0;
                foundObstacle = true;
            } else {
                grid[0][i] = 1;
            }
        }

        foundObstacle = false;

        for (int i = 1; i < grid.length; i++) {
            if (foundObstacle) {
                grid[i][0] = 0;
            } else if (grid[i][0] == 1) {
                grid[i][0] = 0;
                foundObstacle = true;
            } else {
                grid[i][0] = 1;
            }
        }
    }
}
