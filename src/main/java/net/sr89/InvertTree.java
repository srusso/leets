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

        TreeNode lastPopped = null;

        while (!stack.isEmpty()) {
            TreeNode node = stack.element();

            if (node.left == null && node.right == null) {
                lastPopped = stack.pop();
                continue;
            }

            final boolean upFromLeft = lastPopped == node.left;
            final boolean upFromRight = lastPopped == node.right;

            if (upFromLeft && node.right != null) {
                stack.push(node.right);
                continue;
            } if (upFromLeft) {
                swap(node);
                lastPopped = stack.pop();
                continue;
            } else if (upFromRight) {
                swap(node);
                lastPopped = stack.pop();
                continue;
            }

            if (node.left != null) {
                stack.push(node.left);
            } else {
                stack.push(node.right);
            }
        }

        return root;
    }

    private void swap(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }
}
