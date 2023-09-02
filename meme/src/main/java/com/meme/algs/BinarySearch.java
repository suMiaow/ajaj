package com.meme.algs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BinarySearch {

    public static int binarySearchBasic(int[] a, int target) {

        int i = 0;
        int j = a.length - 1;
        int m;

        do {
            m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }

        } while (i <= j);


        return -1;
    }

    public static int binarySearchAlternative(int[] a, int target) {

        int i = 0;
        int j = a.length;
        int m;

        do {
            m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                return m;
            }

        } while (i < j);


        return -1;
    }

    public static int binarySearch3(int[] a, int target) {

        int i = 0;
        int j = a.length;
        int m;

        do {
            m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m;
            } else {
                i = m;
            }

        } while (j - i > 1);

        if (a[i] == target) {
            return i;
        } else {
            return -1;
        }
    }


    public static int binarySearchLeftMost(int[] a, int target) {

        int i = 0;
        int j = a.length - 1;
        int m;

        do {
            m = (i + j) >>> 1;
            if (target <= a[m]) {
                j = m - 1;
            } else  {
                i = m + 1;
            }

        } while (i <= j);


        return i;
    }

    public static int binarySearchRightMost(int[] a, int target) {

        int i = 0;
        int j = a.length - 1;
        int m;
        int candidate = -1;

        do {
            m = (i + j) >>> 1;
            if (target < a[m]) {
                j = m - 1;
            } else if (a[m] < target) {
                i = m + 1;
            } else {
                candidate = m;
                i = m + 1;
            }

        } while (i <= j);


        return candidate;
    }

    public static void main(String[] args) {
        int[] a = new int[2];
    }


}
