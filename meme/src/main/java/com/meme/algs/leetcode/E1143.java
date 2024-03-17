package com.meme.algs.leetcode;

import java.util.Arrays;

public class E1143 {
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.isEmpty() || text2 == null || text2.isEmpty()) {
            return 0;
        }

        int[] dp;
        String a;
        String b;
        if (text1.length() > text2.length()) {
            a = text1;
            b = text2;
            dp = new int[text2.length()];
        } else {
            a = text2;
            b = text1;
            dp = new int[text1.length()];
        }

        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        for (char aChar : aChars) {
            int prev = 0;
            for (int i = 0; i < bChars.length; i++) {
                int curr = dp[i];
                if (aChar == bChars[i]) {
                    curr = prev + 1;
                } else {
                    if (i != 0) {
                        curr = Math.max(dp[i], dp[i - 1]);
                    }
                }
                prev = dp[i];
                dp[i] = curr;
            }
        }

        return dp[b.length() - 1];
    }

    public static void main(String[] args) {
        longestCommonSubsequence("bl", "yby");
    }
}
