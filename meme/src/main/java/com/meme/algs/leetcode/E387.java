package com.meme.algs.leetcode;

public class E387 {

    public int firstUniqChar(String s) {
        int[] array = new int[26];
        char[] charArray = s.toCharArray();
        for (char c :charArray){
            array[c - 97]++;
        }

        for (int i = 0; i < charArray.length; i++) {
            if (array[charArray[i] - 97] == 1) {
                return i;
            }
        }

        return -1;
    }
}
