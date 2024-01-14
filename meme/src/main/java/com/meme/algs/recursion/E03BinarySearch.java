package com.meme.algs.recursion;

public class E03BinarySearch {

     public static int search(int[] a, int target) {
         return f(a, target, 0, a.length - 1);
     }

     private static int f(int[] a, int target, int lo, int hi) {

         if (lo <= hi) {
             int m = (lo + hi) >>> 1;
             if (target == a[m]) {
                 return m;
             } else if (target < a[m]) {
                 return f(a, target, lo, m - 1);
             } else {
                 return f(a, target, m + 1, hi);
             }
         }

         return -1;
     }

    public static void main(String[] args) {
        int search = search(new int[]{1,2,3,4,5,6}, 3);
        System.out.println(search);
    }
}
