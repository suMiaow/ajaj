package com.meme.algs;

import java.util.Arrays;

import static com.meme.algs.common.AlgsUtil.merge;

public class MergeInsertionSort {

    public static void insertion(int[] a, int begin, int end) {
        for (int i = begin + 1, low = begin; i < end; i++, low++) {
            int iValue = a[i];
            if (iValue < a[low]) {
                int j;
                for (j = i - 1; j >= begin; j--) {
                    if (a[j] > iValue) {
                        a[j + 1] = a[j];
                    } else {
                        break;
                    }
                }
                a[j + 1] = iValue;
            }
        }
    }

    public static void sort(int[] a) {
        int[] tmp = new int[a.length];
        split(a, 0, a.length, tmp);
    }

    private static void split(int[] a, int begin, int end, int[] tmp) {

        if (end - begin <= 16) {
            insertion(a, begin, end);
            return;
        }

        int m = (begin + end) >>> 1;
        split(a, begin, m, tmp);
        split(a, m, end, tmp);
        merge(a, begin, m, m, end, tmp);
        System.arraycopy(tmp, begin, a, begin, end - begin);
    }

    public static void main(String[] args) {

        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34, 3, 6, 2, 4, 6, 2, 9, 3, 5, 21, 7, 2, 7, 34, 7, 3, 5, 8, 4, 3, 2, 94, 6, 4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
