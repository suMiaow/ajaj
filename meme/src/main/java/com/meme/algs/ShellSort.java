package com.meme.algs;

import java.util.Arrays;

public class ShellSort {

    public static void sort(int[] a) {
        for (int gap = a.length >> 1; gap > 0; gap >>= 1) {

            for (int i = gap, low = 0; i < a.length; i++, low++) {
                int iValue = a[i];
                if (iValue < a[low]) {
                    int j;
                    for (j = i - gap; j >= 0; j -= gap) {
                        if (a[j] > iValue) {
                            a[j + gap] = a[j];
                        } else {
                            break;
                        }
                    }
                    a[j + gap] = iValue;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
