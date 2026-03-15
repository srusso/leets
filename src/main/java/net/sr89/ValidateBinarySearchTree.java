package net.sr89;

import net.sr89.types.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/validate-binary-search-tree/">Leetcode link</a>
 */
public class ValidateBinarySearchTree {
    private record MinMax(TreeNode node, int min, int max) {
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }

        Deque<MinMax> stack = new LinkedList<>();

        stack.push(new MinMax(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        MinMax lastPopped = null;

        while (!stack.isEmpty()) {
            var stackTop = stack.element();
            var currentAllowedMax = stackTop.max;
            var currentAllowedMin = stackTop.min;
            var currentTreeNode = stackTop.node;
            var lastPoppedTreeNode = lastPopped == null ? null : lastPopped.node;

            final boolean upFromLeft = lastPoppedTreeNode == currentTreeNode.left && currentTreeNode.left != null;
            final boolean upFromRight = lastPoppedTreeNode == currentTreeNode.right && currentTreeNode.right != null;

            if (currentTreeNode.left == null && currentTreeNode.right == null) {
                // Visiting terminal node
                lastPopped = stack.pop();

                if (currentTreeNode.val <= currentAllowedMin || currentTreeNode.val >= currentAllowedMax) {
                    return false;
                }
            } else if (upFromLeft && currentTreeNode.right != null) {
                // We just visited the left subtree and there is a right subtree

                stack.push(new MinMax(currentTreeNode.right, currentTreeNode.val, currentAllowedMax));
            } else if (upFromLeft || upFromRight) {
                // We just visited both available subtrees
                lastPopped = stack.pop();

                if (currentTreeNode.val <= currentAllowedMin || currentTreeNode.val >= currentAllowedMax) {
                    return false;
                }
            } else if (currentTreeNode.left != null) {
                // you go left, the minimum stays the same, the maximum changes
                stack.push(new MinMax(currentTreeNode.left, currentAllowedMin, currentTreeNode.val));
            } else {
                stack.push(new MinMax(currentTreeNode.right, currentTreeNode.val, currentAllowedMax));
            }
        }

        return true;
    }
}
