package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E145 {
    public static List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode pop = null;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                    result.add(pop.val);
                } else {
                    curr = peek.right;
                }
            }
        }
        return result;
    }
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

        System.out.println(postorderTraversal(root));

    }
}
