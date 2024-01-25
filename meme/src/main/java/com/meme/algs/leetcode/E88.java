package com.meme.algs.leetcode;

import java.util.Arrays;

public class E88 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i1 = 0;
        int i2 = 0;

        while (i2 < n && i1 < m + n) {
            if (i1 - i2 < m) {
                if (nums1[i1] >= nums2[i2]) {
                    for (int i = m + i2; i > i1; i--) {
                        nums1[i] = nums1[i - 1];
                    }
                    nums1[i1] = nums2[i2];
                    i2++;
                }
            } else {
                nums1[i1] = nums2[i2];
                i2++;
            }
            i1++;
        }
    }

    public static void main(String[] args) {


        int[] a1 = new int[]{1, 1, 3, 0, 0, 0};
        int[] a2 = new int[]{2, 5, 6};

        merge(a1, 3, a2, 3);

        System.out.println(Arrays.toString(a1));

    }
}
