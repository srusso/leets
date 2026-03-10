package net.sr89;

import net.sr89.types.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class InvertTree {

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
