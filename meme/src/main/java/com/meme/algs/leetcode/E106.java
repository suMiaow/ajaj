package com.meme.algs.leetcode;

import com.meme.algs.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class E106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return buildTree(inorder, 0, inorder.length, postorder, 0, postorder.length, inMap);
    }

    public TreeNode buildTree(int[] inorder, int ini, int inj, int[] postorder, int posti, int postj, Map<Integer, Integer> inMap) {
        if (ini >= inj || posti >= postj) {
            return null;
        }
        int rootVal = postorder[postj - 1];
        int inRooti = inMap.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorder, ini, inRooti, postorder, posti, posti + inRooti - ini, inMap);
        root.right = buildTree(inorder, inRooti + 1, inj, postorder, posti + inRooti - ini, postj - 1, inMap);

        return root;
    }
}
