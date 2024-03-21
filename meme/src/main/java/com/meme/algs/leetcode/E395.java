package com.meme.algs.leetcode;

public class E395 {

    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        int[] counts = new int[26];

        char[] chars = s.toCharArray();
        for (char c : chars) {
            counts[c - 'a']++;
        }


        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int count = counts[c - 'a'];
            if (count > 0 && count < k) {
                int j = i + 1;
                while (j < chars.length && counts[chars[j] - 'a'] < k) {
                    j++;
                }
                String sa = s.substring(0, i);
                String sb = s.substring(j);
                return Math.max(sa.length(), longestSubstring(sb, k));
            }
        }
        return s.length();
    }
}
