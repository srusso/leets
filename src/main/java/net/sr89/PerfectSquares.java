package net.sr89;

import java.util.*;

/**
 * https://leetcode.com/problems/perfect-squares/
 */
public class PerfectSquares {

    private static final Set<Integer> squaresSet = new HashSet<>();
    private Map<Integer, Integer> solutions = new HashMap<>();

    static {
        for (int i = 0; i < 100; i++) {
            squaresSet.add(i * i);
        }
    }

    public int numSquares(int n) {
        solutions.put(1, 1);
        solutions.put(2, 2);
        solutions.put(3, 3);
        solutions.put(4, 1);
        solutions.put(5, 2);

        for (int i = 6; i <= n; i++) {
            int minSolution = Integer.MAX_VALUE;
            if (squaresSet.contains(i)) {
                minSolution = 1;
            } else {
                for (int j = 1; j < i; j++) {
                    minSolution = Math.min(
                            minSolution,
                            solutions.get(j) + solutions.get(i - j));
                }
            }
            solutions.put(i, minSolution);
        }

        return solutions.get(n);
    }
}
