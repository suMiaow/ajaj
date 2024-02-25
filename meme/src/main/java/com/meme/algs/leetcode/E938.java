package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.LinkedList;

public class E938 {

    public int rangeSumBST(TreeNode root, int low, int high) {

        int sum = 0;

        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode pop = stack.pop();
                int val = pop.val;
                if (val >= low && val <= high) {
                    sum += val;
                } else if (val > high) {
                    break;
                }
                curr = pop.right;
            }
        }

        return sum;
    }

    public int rangeSumBST1(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val > high) {
            return rangeSumBST1(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST1(root.right, low, high);
        }
        return root.val + rangeSumBST1(root.left, low, high) + rangeSumBST1(root.right, low, high);
    }

}
