package com.meme.algs;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.meme.algs.common.AlgsUtil.swap;

public class QuickSortHoare {

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
        if (end - begin <= 1) {
            return begin;
        }

        int randomIndex = ThreadLocalRandom.current().nextInt(begin, end);
        swap(a, begin, randomIndex);

        int pv = a[begin];

        int i = begin + 1;
        int j = end - 1;

        while (i <= j) {
            while (i <= j && a[i] < pv) {
                i++;
            }
            while (i <= j && a[j] > pv) {
                j--;
            }
            if (i <= j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }
        swap(a, begin, j);
        return j;
    }

    public static void main(String[] args) {

        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34, 3, 6, 2, 4, 6, 2, 9};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
