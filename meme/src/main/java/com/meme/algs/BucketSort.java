package com.meme.algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BucketSort {

    public static void sort(int[] a, int range) {

        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            int v = a[i];
            if (v > max) {
                max = v;
            }
            if (v < min) {
                min = v;
            }
        }

        ArrayList<Integer>[] buckets = new ArrayList[(max - min) / range + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int v : a) {
            buckets[(v - min) / range].add(v);
        }

        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        int i = 0;
        for (ArrayList<Integer> bucket : buckets) {
            for (Integer v : bucket) {
                a[i++] = v;
            }
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{83, 5, -3, 1, 6, -48, 3, 6, 4, 3, -1, 3, 0, -4};
        sort(a, 4);
        System.out.println(Arrays.toString(a));
    }
}
