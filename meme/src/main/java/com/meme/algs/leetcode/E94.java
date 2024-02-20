package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E94 {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                curr = pop.right;
            }
        }

        return result;
    }
}
