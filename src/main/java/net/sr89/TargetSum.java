package net.sr89;

/**
 * <a href="https://leetcode.com/problems/target-sum/description/">Leetcode link</a>
 */
public class TargetSum {
    private static final int SIZE = 1001;

    public int findTargetSumWays(int[] nums, int target) {
        int[][] ways = new int[2][SIZE];

        int which = 0;

        if (nums[0] == 0) {
            ways[which][nums[0]] += 2;
        } else {
            ways[which][nums[0]]++;
        }

        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];

            for (int k = 0; k < SIZE; k++) {
                ways[1 - which][k] = ways(ways[which], k + n) + ways(ways[which], k - n);
            }

            which = 1 - which;
        }

        return ways(ways[which], target);
    }

    private int ways(int[] ways, int i) {
        if (i >= SIZE) {
            return 0;
        }
        return i >= 0 ? ways[i] : ways[-i];
    }
}
