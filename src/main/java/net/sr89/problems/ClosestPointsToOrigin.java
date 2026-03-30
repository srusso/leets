package net.sr89.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        var closest = new PriorityQueue<int[]>(
                (o1, o2) -> {
                    if (o1[0] * o1[0] < o2[0] * o2[0]) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
        );

        closest.addAll(Arrays.asList(points));

        var closestPoints = new int[k][];

        for (int i = 0; i < k; i++) {
            closestPoints[i] = closest.poll();
        }

        return closestPoints;
    }

    private double distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
