package com.meme.algs.leetcode;

import java.util.HashMap;

public class E3 {

    public static int lengthOfLongestSubstring(String s) {
        int longest = 0;
        int begin = 0;
        int end = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                Integer j = map.get(c);
                if (j >= begin) {
                    begin = j + 1;
                }
            }
            end++;
            longest = Math.max(longest, end - begin);
            map.put(c, i);
        }

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdabc"));
    }
}
