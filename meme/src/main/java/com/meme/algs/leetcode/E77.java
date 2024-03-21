package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E77 {

    public static List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> result = new ArrayList<>();
        dfs(n, k, 1, new LinkedList<>(), result);

        return result;
    }

    static void dfs(int n, int k, int begin, LinkedList<Integer> stack, List<List<Integer>> result) {

        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
            return;
        }

        for (int i = begin; i <= n; i++) {
            if (stack.size() + n + 1 - begin < k) {
                return;
            }
            stack.push(i);
            dfs(n, k, i + 1, stack, result);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
    }
}
