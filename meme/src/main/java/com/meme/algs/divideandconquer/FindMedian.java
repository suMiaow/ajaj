package com.meme.algs.divideandconquer;

public class FindMedian {

    public static double findMedian(int[] nums) {
        if (nums.length % 2 == 1) {
            return QuickSelect.quick(nums, 0, nums.length, nums.length / 2);
        } else {
            int after = QuickSelect.quick(nums, 0, nums.length, nums.length / 2);
            int before = QuickSelect.quick(nums, 0, nums.length, nums.length / 2 - 1);

            return (before + after) / 2.0;
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedian(new int[]{1, 2, 4}));
        System.out.println(findMedian(new int[]{1, 2, 5, 8, 4}));
        System.out.println(findMedian(new int[]{1, 3, 2, 5, 8, 4}));
    }
}
