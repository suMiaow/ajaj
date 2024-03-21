package com.meme.algs.leetcode;

public class E50 {

    public static double myPow(double x, int n) {
        return n < 0 ? 1 / myPowNatural(x, n) : myPowNatural(x, n);
    }

    public static double myPowNatural(double x, int n) {

        if (n == 0) {
            return 1;
        }

        int a = n / 2;
        double powa = myPowNatural(x, a);
        if ((n & 1) == 0) {
            return powa * powa;
        } else {
            return powa * powa * x;
        }
    }

    public static void main(String[] args) {
        System.out.println(myPow(2, -3));
        System.out.println(myPow(2, -2));
        System.out.println(myPow(2, -1));
        System.out.println(myPow(2, 0));
        System.out.println(myPow(2, 1));
        System.out.println(myPow(2, 2));
        System.out.println(myPow(2, 3));
        System.out.println(myPow(2, 4));
        System.out.println(myPow(2, 5));
        System.out.println(myPow(2, 6));
    }
}
