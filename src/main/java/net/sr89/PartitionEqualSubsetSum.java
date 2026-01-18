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

        return canPartitionRec(nums, wantedSum, 0, 0)
                || canPartitionRec(nums, wantedSum, 0, 1);
    }


    public boolean canPartitionRec(int[] nums, int wantedSum, int currentSum, int nextIdx) {
        if (nextIdx >= nums.length) {
            return false;
        }

        int newSum = currentSum + nums[nextIdx];

        if (newSum == wantedSum) {
            return true;
        } else if (newSum < wantedSum) {
            return canPartitionRec(nums, wantedSum, currentSum, nextIdx + 1)
                    || canPartitionRec(nums, wantedSum, newSum, nextIdx + 1);
        } else {
            return canPartitionRec(nums, wantedSum, currentSum, nextIdx + 1);
        }

    }
}
