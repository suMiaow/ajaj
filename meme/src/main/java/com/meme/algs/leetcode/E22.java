package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.List;

public class E22 {
    public static List<String> generateParenthesis(int n) {
        if (n < 1) {
            return List.of("");
        }
        List<String>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>(List.of(""));
        dp[1] = new ArrayList<>(List.of("()"));

        for (int i = 2; i <= n; i++) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String pa :  dp[j]) {
                    String prefix = "(" + pa + ")";
                    for (String pb : dp[i - 1 - j]) {
                        dp[i].add(prefix + pb);
                    }
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(generateParenthesis(i));
        }
    }
}

