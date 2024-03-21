package com.meme.algs.leetcode;

import com.meme.algs.MinHeap;
import com.meme.algs.divideandconquer.QuickSelect;

public class E215 {

    public static int findKthLargest(int[] nums, int k) {
        MinHeap minHeap = new MinHeap(k);
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.replace(nums[i]);
            }
        }

        return minHeap.peek();
    }

    public static int findKthLargest1(int[] nums, int k) {
        return QuickSelect.quick(nums, 0, nums.length, nums.length - k);
    }


    public static void main(String[] args) {
        findKthLargest1(new int[] {99, 99}, 1);
    }
}
