package net.sr89;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        final int total = Arrays.stream(nums).sum();

        if (total % 2 == 1) {
            return false;
        }

        final int wantedSum = total / 2;

        int[][] solutions = new int[200][200];

        

        return false;
    }
}
