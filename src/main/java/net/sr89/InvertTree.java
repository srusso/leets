package net.sr89;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class InvertTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, left, right);
        }

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    "," + (left == null ? "_" : left) +
                    "," + (right == null ? "_" : right) +
                    '}';
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);

        TreeNode lastPopped = null;

        while (!stack.isEmpty()) {
            var node = stack.element();

            final boolean upFromLeft = lastPopped == node.left && node.left != null;
            final boolean upFromRight = lastPopped == node.right && node.right != null;

            if (node.left == null && node.right == null) {
                // Visiting terminal node, nothing to do but go back up the stack.
                lastPopped = stack.pop();
            } else if (upFromLeft && node.right != null) {
                // We just visited the left subtree and there is a right subtree, so visit that now.
                stack.push(node.right);
            } else if (upFromLeft || upFromRight) {
                // We just visited both available subtrees, time to swap and go back up the stack.
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;

                lastPopped = stack.pop();
            } else if (node.left != null) {
                stack.push(node.left);
            } else {
                stack.push(node.right);
            }
        }

        return root;
    }
}
