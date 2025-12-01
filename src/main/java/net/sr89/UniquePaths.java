package net.sr89;

import java.util.Arrays;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        final var grid = new int[m][n];

        Arrays.fill(grid[0], 1);

        for (int i = 0; i < grid.length; i++) {
            grid[i][0] = 1;
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }

        return grid[m - 1][n - 1];
    }
}
