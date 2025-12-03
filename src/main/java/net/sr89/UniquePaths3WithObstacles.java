package net.sr89;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * <a href="https://leetcode.com/problems/unique-paths-iii/description/">Leetcode link</a>
 */
public class UniquePaths3WithObstacles {
    private static final int EMPTY = 0;
    private static final int START = 1;
    private static final int END = 2;
    private static final int OBSTACLE = -1;

    public int uniquePathsIII(int[][] grid) {
        Point start = findStartingPoint(grid);
        int obstacles = countObstacles(grid);

        HashSet<Point> visited = new HashSet<>();
        visited.add(start);

        return uniquePathsIII(grid, start, visited, obstacles);
    }

    private int countObstacles(int[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == OBSTACLE) {
                    count++;
                }
            }
        }

        return count;
    }

    private int uniquePathsIII(int[][] grid, Point current, Set<Point> visited, int obstacles) {
        if (current.of(grid) == END && visited.size() == (grid.length * grid[0].length - obstacles)) {
            System.out.println("Found path!");
            return 1;
        }

        return Stream.of(current.up(), current.down(), current.left(), current.right())
                .mapToInt(nextPoint -> {
                    if (canVisit(grid, nextPoint, visited)) {
                        visited.add(nextPoint);
                        int pathsToGoal = uniquePathsIII(grid, nextPoint, visited, obstacles);
                        visited.remove(nextPoint);
                        return pathsToGoal;
                    } else {
                        return 0;
                    }
                }).sum();
    }

    private boolean canVisit(int[][] grid, Point point, Set<Point> visited) {
        return isWithinBounds(grid, point)
                && (point.of(grid) == EMPTY || point.of(grid) == END)
                && !visited.contains(point);
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

        public int of(int[][] grid) {
            return grid[x][y];
        }
    }
}
