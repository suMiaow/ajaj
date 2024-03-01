package com.meme.algs.leetcode;

import java.util.Arrays;

public class E242 {

    public boolean isAnagram(String s, String t) {
        char[] charArray1 = s.toCharArray();
        Arrays.sort(charArray1);
        char[] charArray2 = t.toCharArray();
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }
}
