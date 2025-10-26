package net.sr89;

/**
 * <a href="https://leetcode.com/problems/target-sum/description/">Leetcode link</a>
 */
public class TargetSum {
    public int findTargetSumWays(int[] nums, int target) {
        int[][] ways = new int[1001][2];

        int which = 0;

        if (nums[0] == 0) {
            ways[nums[0]][which] += 2;
        } else {
            ways[nums[0]][which]++;
        }

        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            for (int k = 0; k < ways.length; k++) {
                if (n != 0) {
                    ways[k][1 - which] = ways(ways, k + n, which) + ways(ways, k - n, which);
                } else {
                    ways[k][1 - which] = ways(ways, k, which) * 2;
                }
            }

            which = 1 - which;
        }

        return ways(ways, target, which);
    }

    private int ways(int[][] ways, int i, int which) {
        if (i >= ways.length) {
            return 0;
        }
        return i >= 0 ? ways[i][which] : ways[-i][which];
    }
}
