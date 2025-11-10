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
                Point point = new Point(i, j);
                if (isWater(grid, point)) {
                    continue;
                }

                maxIsland = Math.max(maxIsland, exploreIsland(grid, point));
            }
        }

        return maxIsland;
    }

    private int exploreIsland(int[][] grid, Point startingPoint) {
        int size = 0;

        Queue<Point> pointsToExplore = new LinkedList<>();
        pointsToExplore.add(startingPoint);

        while (!pointsToExplore.isEmpty()) {
            Point point = pointsToExplore.poll();

            if (isWithinBounds(grid, point) && !isWater(grid, point)) {
                size++;
                grid[point.i][point.j] = 0;

                pointsToExplore.add(point.up());
                pointsToExplore.add(point.down());
                pointsToExplore.add(point.left());
                pointsToExplore.add(point.right());
            }
        }

        return size;
    }

    private boolean isWithinBounds(int[][] grid, Point point) {
        return point.i >= 0 && point.i < grid.length && point.j >= 0 && point.j < grid[0].length;
    }

    private boolean isWater(int[][] grid, Point point) {
        return grid[point.i][point.j] == 0;
    }

    record Point(int i, int j) {
        public Point up() {
            return new Point(i - 1, j);
        }

        public Point down() {
            return new Point(i + 1, j);
        }

        public Point left() {
            return new Point(i, j - 1);
        }

        public Point right() {
            return new Point(i, j + 1);
        }
    }
}
