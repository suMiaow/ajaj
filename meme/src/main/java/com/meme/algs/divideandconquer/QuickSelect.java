package com.meme.algs.divideandconquer;

import java.util.concurrent.ThreadLocalRandom;

import static com.meme.algs.common.AlgsUtil.swap;

public class QuickSelect {

    public static int quick(int[] array, int left, int right, int i) {

        int pivot = partition(array, left, right);
        if (pivot == i) {
            return array[i];
        } else if (pivot < i) {
            return quick(array, pivot + 1, right, i);
        } else {
            return quick(array, 0, pivot, i);
        }
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

        int[] a = {9, 2, 1, 6, 5};
        System.out.println(quick(a, 0, a.length, 0));
        System.out.println(quick(a, 0, a.length, 1));
        System.out.println(quick(a, 0, a.length, 2));
        System.out.println(quick(a, 0, a.length, 3));
        System.out.println(quick(a, 0, a.length, 4));
    }
}
