package net.sr89.problems;

/**
 * <a href="https://leetcode.com/problems/unique-paths-iii/description/">Leetcode link</a>
 */
public class UniquePaths3WithObstacles {
    private static final int EMPTY = 0;
    private static final int START = 1;
    private static final int END = 2;
    private static final int OBSTACLE = -1;
    private static final int VISITED = 3;
    private static final int VISITED_END = VISITED + END;

    int obstacles;
    int[][] grid;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        Point start = findStartingPoint();

        grid[start.x][start.y] = VISITED;

        return uniquePathsIII(start.x, start.y, 1);
    }

    private int uniquePathsIII(int x, int y, int visited) {
        if (grid[x][y] == VISITED_END) {
            if (visited == (grid.length * grid[0].length - obstacles)) {
                return 1;
            } else {
                // break early if you meet the end without having visited every other location first
                return 0;
            }
        }

        int total = 0;

        if (canVisit(x - 1, y)) {
            int status = grid[x - 1][y];
            if (status == END) {
                grid[x - 1][y] = VISITED_END;
            } else {
                grid[x - 1][y] = VISITED;
            }
            total += uniquePathsIII(x - 1, y, visited + 1);
            grid[x - 1][y] = status;
        }

        if (canVisit(x + 1, y)) {
            int status = grid[x + 1][y];
            if (status == END) {
                grid[x + 1][y] = VISITED_END;
            } else {
                grid[x + 1][y] = VISITED;
            }
            total += uniquePathsIII(x + 1, y, visited + 1);
            grid[x + 1][y] = status;
        }

        if (canVisit(x, y - 1)) {
            int status = grid[x][y - 1];
            if (status == END) {
                grid[x][y - 1] = VISITED_END;
            } else {
                grid[x][y - 1] = VISITED;
            }
            total += uniquePathsIII(x, y - 1, visited + 1);
            grid[x][y - 1] = status;
        }

        if (canVisit(x, y + 1)) {
            int status = grid[x][y + 1];
            if (status == END) {
                grid[x][y + 1] = VISITED_END;
            } else {
                grid[x][y + 1] = VISITED;
            }
            total += uniquePathsIII(x, y + 1, visited + 1);
            grid[x][y + 1] = status;
        }

        return total;
    }

    private boolean canVisit(int x, int y) {
        return isWithinBounds(x, y)
                && (grid[x][y] == EMPTY || grid[x][y] == END);
    }

    private boolean isWithinBounds(int x, int y) {
        return x < grid.length && x >= 0 && y >= 0 && y < grid[0].length;
    }

    private Point findStartingPoint() {
        Point start = null;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == START) {
                    start = new Point(i, j);
                } else if (grid[i][j] == OBSTACLE) {
                    obstacles++;
                }
            }
        }

        return start;
    }

    private record Point(int x, int y) {
    }
}
