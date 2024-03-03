package com.meme.algs.leetcode;

import java.util.ArrayList;

public class E164 {

    public static int maximumGap1(int[] nums) {

        if (nums.length < 2) {
            return 0;
        }

        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int v = nums[i];
            if (v > max) {
                max = v;
            }
            if (v < min) {
                min = v;
            }
        }
        int range = (max - min) / nums.length;
        if (range < 1) {
            range = 1;
        }

        Pair[] buckets = new Pair[(max - min) / range + 1];


        for (int v : nums) {
            int i = (v - min) / range;
            Pair pair;
            if ((pair = buckets[i]) == null) {
                pair = buckets[i] = new Pair();
            }
            pair.add(v);
        }


        int maximumGap = 0;
        int lastMax = buckets[0].max;
        for (int i = 1; i < buckets.length; i++) {
            Pair bucket = buckets[i];
            if (bucket != null) {
                maximumGap = Math.max(bucket.min - lastMax, maximumGap);
                lastMax = bucket.max;
            }
        }


        return maximumGap;
    }

    static class Pair {
        int min = 1000000000;
        int max = 0;

        void add(int num) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
    }

    public static int maximumGap2(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        radixSort(nums);

        int maxinumGap = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int gap = nums[i + 1] - nums[i];
            if (gap > maxinumGap) {
                maxinumGap = gap;
            }
        }

        return maxinumGap;

    }

    public static void radixSort(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = 1; max >= i; i *= 10) {
            for (int num : nums) {
                buckets[(num / i) % 10].add(num);
            }
            int k = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (Integer num : bucket) {
                    nums[k++] = num;
                }
                bucket.clear();
            }
        }

    }


    public int maximumGap3(int[] nums) {

        return 0;
    }

    public int maximumGap4(int[] nums) {

        return 0;
    }

    public static void main(String[] args) {
        int result = maximumGap1(new int[]{15255, 15256, 15257});
        System.out.println(result);
    }

}
