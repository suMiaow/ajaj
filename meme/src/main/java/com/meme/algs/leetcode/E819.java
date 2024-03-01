package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class E819 {

    public String mostCommonWord(String paragraph, String[] banned) {

        Set<String> bannedSet = Set.of(banned);
        HashMap<String, Integer> map = new HashMap<>();

        char[] charArray = paragraph.toLowerCase().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : charArray) {
            if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);
            } else {
                if (!sb.isEmpty()) {
                    String word = sb.toString();
                    if (!bannedSet.contains(word)) {
                        map.compute(word, (k, v) -> v == null ? 1 : v + 1);
                    }
                    sb.setLength(0);
                }
            }
        }
        if (!sb.isEmpty()) {
            String word = sb.toString();
            map.compute(word, (k, v) -> v == null ? 1 : v + 1);
        }


        int max = 0;
        String result = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer count = entry.getValue();
            if (count > max) {
                max = count;
                result = entry.getKey();
            }
        }
        return result;
    }
}
