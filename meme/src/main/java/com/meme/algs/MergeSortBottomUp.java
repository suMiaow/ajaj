package com.meme.algs;

import java.util.Arrays;

import static com.meme.algs.common.AlgsUtil.merge;

public class MergeSortBottomUp {

    public static void sort(int[] a) {
        int[] tmp = new int[a.length];

        for (int i = 1; i < a.length; i *= 2) {
            for (int j = 0; j < a.length; j = j + 2 * i) {
                int middle = Math.min(a.length, j + i);
                int end = Math.min(a.length, j + 2 * i);
                if (end - j > i) {
                    merge(a, j, middle, middle, end, tmp);
                    System.arraycopy(tmp, j, a, j, end - j);
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34, 3, 6, 2, 4, 6, 2, 9};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
