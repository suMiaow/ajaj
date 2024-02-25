package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E1008 {
    public TreeNode bstFromPreorder(int[] preorder) {

        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++) {
            insert(root, preorder[i]);
        }

        return root;
    }

    private void insert(TreeNode root, int val) {
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            if (val < curr.val) {
                prev = curr;
                curr = curr.left;
            } else if (val > curr.val) {
                prev = curr;
                curr = curr.right;
            } else {
                break;
            }
        }
        if (prev == null) {
            return;
        }
        if (val < prev.val) {
            prev.left = new TreeNode(val);
        } else {
            prev.right = new TreeNode(val);
        }
    }


    public TreeNode bstFromPreorder1(int[] preorder) {
        return bstFromPreorder1(preorder, 0, preorder.length);
    }

    public TreeNode bstFromPreorder1(int[] preorder, int begin, int end) {
        if (begin >= end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[begin]);

        int i;
        for (i = begin + 1; i < end; i++) {
            if (preorder[i] > preorder[begin]) {
                break;
            }
        }
        root.left = bstFromPreorder1(preorder, begin + 1, i);
        root.right = bstFromPreorder1(preorder, i, end);
        return root;
    }
}
