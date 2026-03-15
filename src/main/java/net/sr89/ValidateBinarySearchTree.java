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

        while (!stack.isEmpty()) {
            var currentTreeNode = stack.pop();
            var currentAllowedMin = minStack.pop();
            var currentAllowedMax = maxStack.pop();

            boolean lessThanMin = currentAllowedMin != null && currentTreeNode.val <= currentAllowedMin;
            boolean moreThanMax = currentAllowedMax != null && currentTreeNode.val >= currentAllowedMax;
            if (lessThanMin || moreThanMax) {
                return false;
            }

            if (currentTreeNode.left != null) {
                stack.push(currentTreeNode.left);
                minStack.push(currentAllowedMin);
                maxStack.push(currentTreeNode.val);
            }

            if (currentTreeNode.right != null) {
                stack.push(currentTreeNode.right);
                minStack.push(currentTreeNode.val);
                maxStack.push(currentAllowedMax);
            }
        }

        return true;
    }
}
