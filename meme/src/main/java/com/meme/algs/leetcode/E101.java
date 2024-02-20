package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E101 {

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return check(root.left, root.right);
        }

    }

    public static boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            if (left.val == right.val) {
                return check(left.left, right.right) && check(left.right, right.left);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
