package com.meme.algs.leetcode;

import java.util.*;

public class E49 {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            List<String> list = map.computeIfAbsent(String.valueOf(charArray), k -> new ArrayList<>());
            list.add(str);
        }
        return map.values().stream().toList();
    }
}
