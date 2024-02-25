package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.LinkedList;

public class E98 {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return false;
        }

        Integer pPrev = null;
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (pPrev != null && pPrev >= pop.val) {
                    return false;
                }
                pPrev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

    Integer prev = null;

    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftValid = isValidBST1(root.left);
        if (!leftValid) {
            return false;
        }
        if (prev >= root.val) {
            return false;
        }
        prev = root.val;

        return isValidBST1(root.right);
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (max != null && root.val >= max) {
            return false;
        }
        if (min != null && root.val <= min) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

}
