package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E144 {

    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                curr = pop.right;
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

        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.println("in: " + curr);
                stack.push(curr); // preorder
                curr = curr.left;
            } else {
                TreeNode out = stack.pop();
                System.out.println("out: " + out); // inorder
                curr = out.right;

            }
        }
    }
}
