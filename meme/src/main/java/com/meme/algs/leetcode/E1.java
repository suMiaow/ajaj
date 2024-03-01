package com.meme.algs.leetcode;

import java.util.HashMap;

public class E1 {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int sub = target - num;
            if (hashMap.containsKey(sub)) {
                return new int[]{hashMap.get(sub), i};
            }
            hashMap.put(num, i);
        }
        return null;
    }
}
