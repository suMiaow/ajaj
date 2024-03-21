package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E216 {
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> result = new ArrayList<>();
        dfs(k, n, 1, new LinkedList<>(), result);
        return result;
    }

    static void dfs(int k, int n, int begin, LinkedList<Integer> stack, List<List<Integer>> result) {

        if (stack.size() == k) {
            if (n == 0) {
                result.add(new ArrayList<>(stack));
            }
            return;
        }

        for (int i = begin; i <= Math.min(9, n); i++) {
            n -= i;
            stack.push(i);
            dfs(k, n, i + 1, stack, result);
            stack.pop();
            n += i;
        }
    }
}
