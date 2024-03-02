package com.meme.algs;

import java.util.Arrays;

public class CountingSort {

    public static void sort(int[] a) {
        int max = 0;
        int min = 0;
        for (int v : a) {
            if (v > max) {
                max = v;
            }
            if (v < min) {
                min = v;
            }
        }

        int[] counting = new int[max - min + 1];
        for (int v : a) {
            counting[v - min]++;
        }

        int ai = 0;
        for (int i = 0; i < counting.length; i++) {
            if (counting[i] > 0) {
                for (int j = 0; j < counting[i]; j++) {
                    a[ai++] = i + min;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{83, 5, -3, 1, 6, - 48, 3, 6, 4, 3, -1, 3, 0, -4};
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
