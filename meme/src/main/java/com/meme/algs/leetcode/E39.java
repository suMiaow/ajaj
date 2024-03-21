package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, target, 0, new LinkedList<>(), result);
        return result;
    }

    static void dfs(int[] candidates, int target, int start, LinkedList<Integer> stack, List<List<Integer>> result) {

        if (target == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (target < candidate) {
                continue;
            }
            stack.push(candidate);
            target -= candidate;
            dfs(candidates, target, i, stack, result);
            target += candidate;
            stack.pop();
        }
    }
}
