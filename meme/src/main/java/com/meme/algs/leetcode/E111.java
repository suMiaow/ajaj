package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.LinkedList;

public class E111 {

    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            int minLeft = minDepth(root.left);
            int minRight = minDepth(root.right);
            if (minLeft == 0) {
                return 1 + minRight;
            } else if (minRight == 0) {
                return 1 + minLeft;
            } else {
                return 1 + Math.min(minLeft, minRight);
            }
        }

    }

    public int minDepth2(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        if (root != null) {
            queue.offer(root);
            while (!queue.isEmpty()) {
                depth++;
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode pop = queue.poll();
                    if (pop.left == null && pop.right == null) {
                        return depth;
                    }
                    if (pop.left != null) {
                        queue.offer(pop.left);
                    }
                    if (pop.right != null) {
                        queue.offer(pop.right);
                    }
                }
            }
        }

        return depth;
    }
}
