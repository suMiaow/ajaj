package com.meme.algs.leetcode;

public class E167 {
    public int[] twoSum(int[] numbers, int target) {

        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            int result = numbers[i] + numbers[j];
            if (result > target) {
                j--;
            } else if (result < target) {
                i++;
            } else {
                break;
            }
        }
        return new int[]{i + 1, j + 1};
    }
}
