package com.meme.algs.leetcode;

public class E1122 {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] count = new int[1001];

        for (int i : arr1) {
            count[i]++;
        }

        int[] result = new int[arr1.length];
        int k = 0;

        for (int i : arr2) {
            while (count[i] > 0) {
                result[k++] = i;
                count[i]--;
            }
        }

        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                result[k++] = i;
            }
        }

        return result;
    }
}
