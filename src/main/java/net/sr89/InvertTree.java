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
        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left == null && node.right == null) {
                // This is like the "recursion" base case: we are at a leaf node, nothing to do.
            } else if (node.left != null) {
                // Depth first search: we can still go downwards to the left, so let's do it
                stack.push(node.left);
            } else {
                // We cannot go left anymore, but we can go right, so let's do that
                stack.push(node.right);
            }


        }

        return root;
    }
}
