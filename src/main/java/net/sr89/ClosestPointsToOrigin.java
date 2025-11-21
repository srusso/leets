package net.sr89;

import java.util.Arrays;
import java.util.TreeSet;

public class ClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        var closest = new TreeSet<int[]>(
                (o1, o2) -> {
                    if (distance(o1) < distance(o2)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
        );

        closest.addAll(Arrays.asList(points));

        var closestPoints = new int[k][];

        for (int i = 0; i < k; i++) {
            closestPoints[i] = closest.pollFirst();
        }

        return closestPoints;
    }

    private double distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
