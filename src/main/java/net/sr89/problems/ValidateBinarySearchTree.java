package net.sr89.problems;

import net.sr89.types.TreeNode;

/**
 * <a href="https://leetcode.com/problems/validate-binary-search-tree/">Leetcode link</a>
 */
public class ValidateBinarySearchTree {
    private final TreeNode[] stack = new TreeNode[20];
    private final Integer[] minStack = new Integer[20];
    private final Integer[] maxStack = new Integer[20];

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }

        int stackPos = 0;

        stack[stackPos] = root;

        while (stackPos >= 0) {
            var currentTreeNode = stack[stackPos];
            var currentAllowedMin = minStack[stackPos];
            var currentAllowedMax = maxStack[stackPos];
            stackPos--;

            boolean lessThanMin = currentAllowedMin != null && currentTreeNode.val <= currentAllowedMin;
            boolean moreThanMax = currentAllowedMax != null && currentTreeNode.val >= currentAllowedMax;
            if (lessThanMin || moreThanMax) {
                return false;
            }

            if (currentTreeNode.left != null) {
                stackPos++;
                stack[stackPos] = (currentTreeNode.left);
                minStack[stackPos] = (currentAllowedMin);
                maxStack[stackPos] = (currentTreeNode.val);
            }

            if (currentTreeNode.right != null) {
                stackPos++;
                stack[stackPos] = (currentTreeNode.right);
                minStack[stackPos] = (currentTreeNode.val);
                maxStack[stackPos] = (currentAllowedMax);
            }
        }

        return true;
    }
}
