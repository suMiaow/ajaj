package com.meme.algs;

import java.util.Arrays;

import static com.meme.algs.common.AlgsUtil.swap;

public class QuickSortLomuto {

    public static void sort(int[] a) {
        quick(a, 0, a.length);
    }

    private static void quick(int[] a, int begin, int end) {
        if (begin >= end - 1) {
            return;
        }
        int p = partition(a, begin, end);
        quick(a, begin, p);
        quick(a, p + 1, end);
    }

    private static int partition(int[] a, int begin, int end) {

        int pv = a[end - 1];

        int i = begin;
        int j = begin;

        while (j < end - 1) {
            if (a[i] > pv) {
                if (a[j] < pv) {
                    swap(a, i, j);
                    i++;
                }
            } else {
                i++;
            }
            j++;
        }
        swap(a, i, end - 1);

        return i;
    }

    public static void main(String[] args) {

        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34, 3, 6, 2, 4, 6, 2, 9};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
