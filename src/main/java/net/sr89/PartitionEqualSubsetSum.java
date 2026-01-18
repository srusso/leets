package net.sr89;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
    private record Pair(int idx, int sum) {
    }

    private Set<Pair> failed;

    public boolean canPartition(int[] nums) {
        failed = new HashSet<>();

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

        if (failed.contains(new Pair(nextIdx, currentSum))) {
            return false;
        }

        int newSum = currentSum + nums[nextIdx];

        if (newSum == wantedSum) {
            return true;
        } else if (newSum < wantedSum) {
            final boolean currentSumResult = canPartitionRec(nums, wantedSum, currentSum, nextIdx + 1);
            if (currentSumResult) {
                return true;
            } else {
                failed.add(new Pair(nextIdx, currentSum));
            }
            final boolean newSumResult = canPartitionRec(nums, wantedSum, newSum, nextIdx + 1);
            if (newSumResult) {
                return true;
            } else {
                failed.add(new Pair(nextIdx, currentSum));
            }
            return false;
        } else {
            final boolean currentSumResult = canPartitionRec(nums, wantedSum, currentSum, nextIdx + 1);
            if (currentSumResult) {
                return true;
            } else {
                failed.add(new Pair(nextIdx, currentSum));
            }
            return false;
        }
    }
}
