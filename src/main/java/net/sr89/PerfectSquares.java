package net.sr89;

import java.util.*;

/**
 * https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {

    private static final Set<Integer> squaresSet = new HashSet<>();
    private int[] solutions = new int[10001];

    static {
        for (int i = 0; i <= 100; i++) {
            squaresSet.add(i * i);
        }
    }

    public int numSquares(int n) {
        solutions[1]=1;
        solutions[2]=2;
        solutions[3]=3;
        solutions[4]=1;
        solutions[5]=2;

        for (int i = 6; i <= n; i++) {
            int minSolution = Integer.MAX_VALUE;
            if (squaresSet.contains(i)) {
                minSolution = 1;
            } else {
                for (int j = i/2; j < i; j++) {
                    minSolution = Math.min(
                            minSolution,
                            solutions[j] + solutions[i - j]);
                }
            }
            solutions[i] = minSolution;
        }

        return solutions[n];
    }
}
