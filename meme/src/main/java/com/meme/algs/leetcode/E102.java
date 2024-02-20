package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.*;

public class E102 {

    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<TreeNode> treeNodeList = new LinkedList<>();
        if (root != null) {
            treeNodeList.addLast(root);
        }
        int c1 = treeNodeList.size();
        while (!treeNodeList.isEmpty() && c1 > 0) {
            List<Integer> result = new ArrayList<>();
            int c2 = 0;
            for (int i = 0; i < c1; i++) {
                TreeNode node = treeNodeList.pollFirst();
                result.add(node.val);
                if (node.left != null) {
                    treeNodeList.addLast(node.left);
                    c2++;
                }
                if (node.right != null) {
                    treeNodeList.addLast(node.right);
                    c2++;
                }
            }
            c1 = c2;
            resultList.add(result);
        }

        return resultList;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1,
                new TreeNode(2
                ),
                new TreeNode(3,
                        new TreeNode(6),
                        new TreeNode(7)
                )
        );

        System.out.println(levelOrder(root));

    }
}
