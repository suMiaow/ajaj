package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }

        TreeNode s = root.right;
        while (s.left != null) {
            s = s.left;
        }
        s.right = deleteNode(root.right, s.val);
        s.left = root.left;
        return s;
    }
}
