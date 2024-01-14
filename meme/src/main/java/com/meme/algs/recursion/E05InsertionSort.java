package com.meme.algs.recursion;

import java.util.Arrays;

public class E05InsertionSort {

    public static void sort(int[] a) {
        insertion(a, 1);
    }

    private static void insertion(int[] a, int low) {
        if (low < a.length) {
            int rv = a[low];
            int i;
            for (i = low; i > 0; --i) {
                if (a[i - 1] > rv) {
                    a[i] = a[i - 1];
                } else {
                    break;
                }
            }
            if (i < low) {
                a[i] = rv;
            }
            insertion(a, ++low);
        }
    }

    public static void main(String[] args) {
        int[] a = {12, 10, 283, 3, 4, 4, 4, 6};
        sort(a);
        System.out.println(Arrays.toString(a));
    }


}
