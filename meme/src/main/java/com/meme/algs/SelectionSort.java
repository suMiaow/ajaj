package com.meme.algs;

import com.meme.algs.common.AlgsUtil;

import java.util.Arrays;

public class SelectionSort {

    public static void sort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int maxIndex = i;
            for (int j = 0; j < i; j++) {
                if (a[j] > a[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                AlgsUtil.swap(a, maxIndex, i);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
