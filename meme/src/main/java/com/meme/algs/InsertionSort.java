package com.meme.algs;

import java.util.Arrays;

public class InsertionSort {

    public static void sort(int[] a) {
        for (int i = 1, low = 0; i < a.length; i++, low++) {
            int iValue = a[i];
            if (iValue < a[low]) {
                int j;
                for (j = i - 1; j >= 0 ; j--) {
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

    public static void main(String[] args) {

        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
