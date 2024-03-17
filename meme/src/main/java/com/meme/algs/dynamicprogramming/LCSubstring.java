package com.meme.algs.dynamicprogramming;

public class LCSubstring {

    static int lcs(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        int[] dp = new int[bChars.length];

        int result = 0;
        for (char aChar : aChars) {
            int prevLength = 0;
            for (int j = 0; j < bChars.length; j++) {
                int length;
                if (aChar == bChars[j]) {
                    if (j == 0) {
                        length = 1;
                    } else {
                        length = prevLength + 1;
                    }
                } else {
                    length = 0;
                }
                prevLength = dp[j];
                dp[j] = length;
                result = Math.max(result, length);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(lcs("iloveeeyou", "youloveeme"));
    }
}
