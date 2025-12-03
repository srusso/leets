package net.sr89;

import java.util.stream.Stream;

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

    public int uniquePathsIII(int[][] grid) {
        Point start = findStartingPoint(grid);
        int obstacles = countObstacles(grid);

        start.set(grid, VISITED);

        return uniquePathsIII(grid, start, 1, obstacles);
    }

    private int countObstacles(int[][] grid) {
        int count = 0;

        for (int[] row : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                if (row[j] == OBSTACLE) {
                    count++;
                }
            }
        }

        return count;
    }

    private int uniquePathsIII(int[][] grid, Point current, int visited, int obstacles) {
        if (current.at(grid) == VISITED_END && visited == (grid.length * grid[0].length - obstacles)) {
            return 1;
        }

        return Stream.of(current.up(), current.down(), current.left(), current.right())
                .mapToInt(nextPoint -> {
                    if (canVisit(grid, nextPoint)) {
                        int status = nextPoint.at(grid);
                        if (status == END) {
                            nextPoint.set(grid, VISITED_END);
                        } else {
                            nextPoint.set(grid, VISITED);
                        }
                        int pathsToGoal = uniquePathsIII(grid, nextPoint, visited + 1, obstacles);
                        nextPoint.set(grid, status);
                        return pathsToGoal;
                    } else {
                        return 0;
                    }
                }).sum();
    }

    private boolean canVisit(int[][] grid, Point point) {
        return isWithinBounds(grid, point)
                && (point.at(grid) == EMPTY || point.at(grid) == END)
                && point.at(grid) != VISITED;
    }

    private boolean isWithinBounds(int[][] grid, Point point) {
        return point.x < grid.length && point.x >= 0 && point.y >= 0 && point.y < grid[0].length;
    }

    private Point findStartingPoint(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == START) {
                    return new Point(i, j);
                }
            }
        }

        throw new RuntimeException();
    }

    private record Point(int x, int y) {
        private Point up() {
            return new Point(x - 1, y);
        }

        private Point down() {
            return new Point(x + 1, y);
        }

        private Point left() {
            return new Point(x, y - 1);
        }

        private Point right() {
            return new Point(x, y + 1);
        }

        public int at(int[][] grid) {
            return grid[x][y];
        }

        public void set(int [][] grid, int status) {
            grid[x][y] = status;
        }
    }
}
