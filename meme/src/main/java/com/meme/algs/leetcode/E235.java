package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E235 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode curr = root;
        while (true) {
            if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else if (p.val < curr.val && q.val < curr.val){
                curr = curr.left;
            } else {
                break;
            }
        }
        return curr;
    }
}
