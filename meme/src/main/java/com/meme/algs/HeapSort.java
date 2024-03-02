package com.meme.algs;

import java.util.Arrays;

import static com.meme.algs.common.AlgsUtil.swap;

public class HeapSort {


    public static void sort(int[] array) {
        heapify(array, array.length);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            down(array, i, 0);
        }
    }

    private static void heapify(int[] array, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down(array, size, i);
        }
    }

    private static void down(int[] array, int size, int p) {
        int maxp = size / 2 - 1;
        if (p > maxp) {
            return;
        }
        do {
            int l = p * 2 + 1;
            int r = p * 2 + 2;
            int max = p;
            if (r < size) {
                if (array[l] > array[p] && array[l] >= array[r]) {
                    max = l;
                } else if (array[r] > array[p] && array[r] >= array[l]) {
                    max = r;
                }
            } else {
                if (array[l] > array[p]) {
                    max = l;
                }
            }
            if (max != p) {
                swap(array, p, max);
                p = max;
            } else {
                break;
            }
        } while (p <= maxp);

    }

    public static void main(String[] args) {

        int[] a = new int[]{3, 1, 0, 8, 4, 2, 0, 34};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
