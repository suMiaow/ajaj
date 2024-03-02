package com.meme.algs;

import java.util.Arrays;

import static com.meme.algs.common.AlgsUtil.merge;

public class MergeSortTopDown {

    public static void sort(int[] a) {
        int[] tmp = new int[a.length];
        split(a, 0, a.length, tmp);
    }

    private static void split(int[] a, int begin, int end, int[] tmp) {

        if (begin >= end - 1) {
            return;
        }

        int m = (begin + end) >>> 1;
        split(a, begin, m, tmp);
        split(a, m, end, tmp);
        merge(a, begin, m, m, end, tmp);
        System.arraycopy(tmp, begin, a, begin, end - begin);
    }

    public static void main(String[] args) {

        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34, 3, 6, 2};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
