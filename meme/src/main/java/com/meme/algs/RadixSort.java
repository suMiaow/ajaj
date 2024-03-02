package com.meme.algs;

import java.util.ArrayList;
import java.util.Arrays;

public class RadixSort {

    public static void sort(String[] a, int length) {

        ArrayList<String>[] buckets = initBuckets();

        for (String s : a) {
            buckets[s.charAt(length - 1) - '0'].add(s);
        }

        ArrayList<String>[] buckets2 = initBuckets();

        for (int i = length - 2; i >= 0; i--) {
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    buckets2[s.charAt(i) - '0'].add(s);
                }
            }
            ArrayList<String>[] temp;
            temp = buckets;
            buckets = buckets2;
            buckets2 = temp;
            emptyBuckets(buckets2);
        }

        int i = 0;
        for (ArrayList<String> bucket : buckets) {
            for (String s : bucket) {
                a[i++] = s;
            }
        }
    }

    private static ArrayList<String>[] initBuckets() {
        ArrayList<String>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        return buckets;
    }

    private static void emptyBuckets(ArrayList<String>[] buckets) {
        for (ArrayList<String> bucket : buckets) {
            bucket.clear();
        }
    }

    public static void main(String[] args) {

        String[] a = new String[]{
                "1233",
                "8395",
                "3937",
                "1931",
                "0868",
                "0484"
        };
        sort(a, 4);
        System.out.println(Arrays.toString(a));
    }

}
