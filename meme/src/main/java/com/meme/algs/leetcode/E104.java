package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.LinkedList;

public class E104 {

    public int maxDepth1(TreeNode root) {

        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
        }
    }

    public int maxDepth2(TreeNode root) {
        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode pop = null;
        int maxDepth = 0;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                maxDepth = Math.max(maxDepth, stack.size());
                curr = curr.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    curr = peek.right;
                }
            }
        }

        return maxDepth;
    }

    public int maxDepth3(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        int maxDepth = 0;
        if (root != null) {
            queue.offer(root);
            while (!queue.isEmpty()) {
                maxDepth++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.poll();
                    if (pop.left != null) {
                        queue.offer(pop.left);
                    }
                    if (pop.right != null) {
                        queue.offer(pop.right);
                    }
                }
            }
        }

        return maxDepth;
    }
}
