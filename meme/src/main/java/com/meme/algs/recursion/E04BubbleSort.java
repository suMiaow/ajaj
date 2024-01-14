package com.meme.algs.recursion;

import java.util.Arrays;

public class E04BubbleSort {

    public static void sort(int[] a) {
        bubble(a, a.length);
    }

    private static void bubble(int[] a, int length) {

        if (length > 1) {
            int swapIndex = 0;
            for (int i = 0; i < length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                    swapIndex = i + 1;
                }
            }
            bubble(a, swapIndex);
        }
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {2,1,3,4,5,6};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
