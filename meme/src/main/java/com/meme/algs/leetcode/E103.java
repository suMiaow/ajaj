package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E103 {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> resultList = new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        if (root != null) {
            deque.addLast(root);
        }
        boolean leftToRight = true;
        while (!deque.isEmpty()) {
            List<Integer> result = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                if (leftToRight) {
                    TreeNode node = deque.pollFirst();
                    result.add(node.val);
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                } else {
                    TreeNode node = deque.pollLast();
                    result.add(node.val);
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                }
            }
            resultList.add(result);
            leftToRight = !leftToRight;
        }

        return resultList;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)
                ),
                new TreeNode(3,
                        new TreeNode(6),
                        new TreeNode(7)
                )
        );

        System.out.println(zigzagLevelOrder(root));

    }
}
