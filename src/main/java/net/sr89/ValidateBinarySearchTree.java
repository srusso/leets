package net.sr89;

import net.sr89.types.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/validate-binary-search-tree/">Leetcode link</a>
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        Deque<Integer> minStack = new LinkedList<>();
        Deque<Integer> maxStack = new LinkedList<>();

        stack.push(root);
        minStack.push(null);
        maxStack.push(null);

        TreeNode lastPopped = null;

        while (!stack.isEmpty()) {
            var currentTreeNode = stack.element();
            var currentAllowedMin = minStack.element();
            var currentAllowedMax = maxStack.element();

            final boolean upFromLeft = lastPopped == currentTreeNode.left && currentTreeNode.left != null;
            final boolean upFromRight = lastPopped == currentTreeNode.right && currentTreeNode.right != null;

            if (currentTreeNode.left == null && currentTreeNode.right == null) {
                // Visiting terminal node
                lastPopped = stack.pop();
                minStack.pop();
                maxStack.pop();

                boolean lessThanMin = currentAllowedMin != null && currentTreeNode.val <= currentAllowedMin;
                boolean moreThanMax = currentAllowedMax != null && currentTreeNode.val >= currentAllowedMax;
                if (lessThanMin || moreThanMax) {
                    return false;
                }
            } else if (upFromLeft && currentTreeNode.right != null) {
                // We just visited the left subtree and there is a right subtree

                stack.push(currentTreeNode.right);
                minStack.push(currentTreeNode.val);
                maxStack.push(currentAllowedMax);
            } else if (upFromLeft || upFromRight) {
                // We just visited both available subtrees
                lastPopped = stack.pop();
                minStack.pop();
                maxStack.pop();

                boolean lessThanMin = currentAllowedMin != null && currentTreeNode.val <= currentAllowedMin;
                boolean moreThanMax = currentAllowedMax != null && currentTreeNode.val >= currentAllowedMax;
                if (lessThanMin || moreThanMax) {
                    return false;
                }
            } else if (currentTreeNode.left != null) {
                // you go left, the minimum stays the same, the maximum changes
                stack.push(currentTreeNode.left);
                minStack.push(currentAllowedMin);
                maxStack.push(currentTreeNode.val);
            } else {
                stack.push(currentTreeNode.right);
                minStack.push(currentTreeNode.val);
                maxStack.push(currentAllowedMax);
            }
        }

        return true;
    }
}
