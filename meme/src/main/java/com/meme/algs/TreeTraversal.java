package com.meme.algs;

import com.meme.algs.common.TreeNode;

public class TreeTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(
                        2,
                        new TreeNode(4),
                        null
                ),
                new TreeNode(
                        3,
                        new TreeNode(5),
                        new TreeNode(6)
                )
        );

        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

    }

    static void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode + "\t");
            preOrder(treeNode.left);
            preOrder(treeNode.right);
        }
    }

    static void inOrder(TreeNode treeNode) {

        if (treeNode != null) {
            inOrder(treeNode.left);
            System.out.print(treeNode + "\t");
            inOrder(treeNode.right);
        }
    }

    static void postOrder(TreeNode treeNode) {

        if (treeNode != null) {
            postOrder(treeNode.left);
            postOrder(treeNode.right);
            System.out.print(treeNode + "\t");
        }
    }
}
