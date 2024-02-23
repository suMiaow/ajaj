package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E700 {

    public TreeNode searchBST(TreeNode root, int val) {

        TreeNode p = root;
        while (p != null)  {
            if (val < p.val) {
                p = p.left;
            } else if (val > p.val) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }
}
