package com.meme.algs.dynamicprogramming;

public class CutRodProblem {

    private static int cut(int[] values, int n) {
        int[] dp = new int[n + 1];

        for (int i = 0; i < values.length; i++) {
            for (int j = i; j <= n; j++) {
                dp[j] = Math.max(dp[j], values[i] + dp[j - i]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(cut(new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
    }
}
