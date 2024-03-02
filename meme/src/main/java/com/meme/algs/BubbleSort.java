package com.meme.algs;

import java.util.Arrays;

import static com.meme.algs.common.AlgsUtil.swap;

public class BubbleSort {

    private static void bubble(int[] a) {
        int end = a.length - 1;
        int x = 0;
        while (end > 0) {
            for (int i = 0; i < end; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                    x = i;
                }
            }
            end = x;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 0};
        bubble(a);
        System.out.println(Arrays.toString(a));
    }
}
