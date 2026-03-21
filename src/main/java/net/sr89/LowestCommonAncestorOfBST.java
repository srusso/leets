package net.sr89;

import net.sr89.types.TreeNode;

/**
 * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/">Leetcode</a>
 */
public class LowestCommonAncestorOfBST {
    // Let's use the fact this is a binary search tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }
}
