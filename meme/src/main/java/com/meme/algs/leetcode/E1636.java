package com.meme.algs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class E1636 {
    public static int[] frequencySort(int[] nums) {
        int[] count = new int[201];
        for (int num : nums) {
            count[num + 100]++;
        }

        ArrayList<Integer>[] buckets = new ArrayList[101];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = count.length - 1; i >= 0; i--) {
            for (int j = 0; j < count[i]; j++) {
                buckets[count[i]].add(i - 100);
            }
        }

        int[] result = new int[nums.length];
        int k = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (Integer i : bucket) {
                result[k++] = i;
            }
        }

        return result;
    }

    public static int[] frequencySort1(int[] nums) {
        int[] count = new int[201];
        for (int num : nums) {
            count[num + 100]++;
        }

        return Arrays.stream(nums).boxed().sorted((a, b) -> {
            int fa = count[a + 100];
            int fb = count[b + 100];
            if (fa < fb) {
                return -1;
            } else if (fa > fb) {
                return 1;
            } else {
                return b - a;
            }
        }).mapToInt(Integer::intValue).toArray();

    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 1, 2, 2, 2, 3};
        int[] result = frequencySort1(a);
        System.out.println(Arrays.toString(result));

    }
}
