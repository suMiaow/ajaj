package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

public class E105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    public TreeNode buildTree(int[] preorder, int prei, int prej, int[] inorder, int ini, int inj) {
        if (prei >= prej || ini >= inj) {
            return null;
        }
        int root = preorder[prei];
        int inrooti = 0;
        for (int i = ini; i < inj; i++) {
            if (inorder[i] == root) {
                inrooti = i;
                break;
            }
        }
        TreeNode rootNode = new TreeNode(root);

        rootNode.left = buildTree(preorder, prei + 1, prei + 1 + inrooti - ini, inorder, ini, inrooti);
        rootNode.right = buildTree(preorder, prei + 1 + inrooti - ini, prej, inorder, inrooti + 1, inj);

        return rootNode;
    }
}
