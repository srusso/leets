package net.sr89.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
    private Set<Integer>[] failed;

    public boolean canPartition(int[] nums) {
        failed = new Set[200];

        final int total = Arrays.stream(nums).sum();

        if (total % 2 == 1) {
            return false;
        }

        final int wantedSum = total / 2;

        return canPartitionRec(nums, wantedSum, 0, 0);
    }


    private boolean canPartitionRec(int[] nums, int wantedSum, int currentSum, int nextIdx) {
        if (nextIdx >= nums.length) {
            return false;
        }

        if (failed[nextIdx]!= null && failed[nextIdx].contains(currentSum)) {
            return false;
        }

        int newSum = currentSum + nums[nextIdx];

        if (newSum == wantedSum) {
            return true;
        } else if (newSum < wantedSum) {
            final boolean result = canPartitionRec(nums, wantedSum, currentSum, nextIdx + 1)
                    || canPartitionRec(nums, wantedSum, newSum, nextIdx + 1);
            if (!result) {
                if (failed[nextIdx] == null) {
                    failed[nextIdx] = new HashSet<>();
                }
                failed[nextIdx].add(currentSum);
            }
            return result;
        } else {
            final boolean currentSumResult = canPartitionRec(nums, wantedSum, currentSum, nextIdx + 1);
            if (currentSumResult) {
                return true;
            } else {
                if (failed[nextIdx] == null) {
                    failed[nextIdx] = new HashSet<>();
                }
                failed[nextIdx].add(currentSum);
            }
            return false;
        }
    }
}
