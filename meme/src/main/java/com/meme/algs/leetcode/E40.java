package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class E40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, target, 0, new boolean[candidates.length], new LinkedList<>(), result);
        return result;
    }

    static void dfs(int[] candidates, int target, int start, boolean[] used, LinkedList<Integer> stack, List<List<Integer>> result) {

        if (target == 0) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (i > 0 && candidate == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            if (target >= candidate) {
                stack.push(candidate);
                target -= candidate;
                used[i] = true;
                dfs(candidates, target, i + 1, used, stack, result);
                used[i] = false;
                target += candidate;
                stack.pop();
            }
        }
    }
}
