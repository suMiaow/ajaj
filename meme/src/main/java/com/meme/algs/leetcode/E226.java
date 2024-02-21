package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E226 {
    public TreeNode invertTree(TreeNode root) {

        if (root != null) {
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }

        return root;
    }
}
