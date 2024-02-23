package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E701 {

    public static TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode pParent = null;
        TreeNode p = root;
        while (p != null) {
            if (val < p.val) {
                pParent = p;
                p = p.left;
            } else if (val > p.val) {
                pParent = p;
                p = p.right;
            } else {
                return root;
            }
        }

        if (val < pParent.val) {
            pParent.left = new TreeNode(val);
        } else {
            pParent.right = new TreeNode(val);
        }

        return root;
    }


    public static TreeNode insertIntoBST1(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST1(root.left, val);
        } else if (val > root.val) {
            root.right = insertIntoBST1(root.right, val);
        }
        return root;
    }


}
