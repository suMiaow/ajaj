package com.meme.algs4;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j(topic = "algs4 answer 1.1")
class AnswerTest_1_1 {

    @Test
    void e1() {
        log.info("( 0 + 15 ) / 2 :                {}", (0 + 15) / 2);
        log.info("2.0e-6 * 100000000.1 :          {}", 2.0e-6 * 100000000.1);
        log.info("true && false || true && true : {}", true && false || true && true);
    }

    @Test
    void e2() {
        log.info("typeOf((1 + 2.236)/2):   {}", typeOf((1 + 2.236) / 2));
        log.info("typeOf(1 + 2 + 3 + 4.0): {}", typeOf(1 + 2 + 3 + 4.0));
        log.info("typeOf(4.1 >= 4):        {}", typeOf(4.1 >= 4));
        log.info("typeOf(1 + 2 + \"3\"):     {}", typeOf(1 + 2 + "3"));
    }


    private String typeOf(Object o) {
        return o.getClass().getName();
    }

    private boolean e3(int a, int b, int c) {
        return a == b && b == c;
    }

    private boolean e5(double x, double y) {
        return (x > 0 && x < 1) && (y > 0 && y < 1);
    }

    @Test
    void e6() {
        int f = 0;
        int g = 1;
        for (int i = 0; i < 15; i++) {
            log.info("{}", f);
            f = f + g;
            g = f - g;
        }
    }

    @Test
    void e7a() {
        double t = 9.0;
        while (Math.abs(t - 9.0 / t) > .001)
            t = (9.0 / t + t) / 2.0;
        log.info("{}", String.format("%.5f", t));
        log.info("{}", Math.sqrt(9.0));
    }

    @Test
    void e7b() {
        int sum = 0;
        for (int i = 1; i < 1000; i++)
            for (int j = 0; j < i; j++)
                sum++;
        log.info("{}", sum);
        log.info("{}", 1000 * 500 - 500);
    }

    @Test
    void e7c() {
        int sum = 0;
        for (int i = 1; i < 1000; i *= 2)
            for (int j = 0; j < i; j++)
                sum++;
        log.info("{}", sum);
        log.info("{}", 1 + 2 + 4 + 8 + 16 + 32 + 64 + 128 + 256 + 512);
    }

    @Test
    void e8() {
        log.info("{}", 'b');
        log.info("{}", ('b' + 'c'));
        log.info("{}", (char) ('a' + 4));
    }

    @Test
    void e9() {
        int N = 4;
        log.info("{}", Integer.toBinaryString(N));
        log.info("{}", toBinaryString(N));
    }

    private String toBinaryString(int N) {
        String s = "";
        for (int n = N; n > 0; n /= 2)
            s = (n % 2) + s;
        return s;
    }

    @Test
    void e12() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;
        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];
        log.info("{}", a);
    }

    @Test
    void e13() {
        int M = 5;
        int N = 3;
        int[][] a = {
                {1, 3, 5},
                {6, 23, 6},
                {2, 52, 35},
                {21, 74, 3},
                {3, 631, 6}
        };
        for (int[] aa : a) {
            log.info("{}", aa);
        }

        int[][] t = transposition(a, M, N);
        for (int[] tt : t) {
            log.info("{}", tt);
        }
    }

    int[][] transposition(int[][] a, int M, int N) {
        int[][] t = new int[N][M];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                t[j][i] = a[i][j];
            }
        }
        return t;
    }

    @Test
    void e14() {
        int size = 100;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            log.info("{}", lgCeiling(i));
            result[i] = lgCeiling(i);
        }
    }

    private int lgCeiling(int N) {
        int result = 0;
        for (int i = N; i > 1; i /= 2) {
            result += 1;
        }
        return result;
    }

    @Test
    void e15() {
        int[] a = {1, 2, 1, 5, 2, 3,};
        int[] histogram = histogram(a, 6);
        log.info("{}", histogram);
    }

    private int[] histogram(int[] a, int M) {
        int[] result = new int[M];
        for (int i : a) {
            if (i >= 0 && i < M) {
                result[i] += 1;
            }
        }

        return result;
    }

    @Test
    void e16() {
        log.info("{}", exR1(6));
    }

    public String exR1(int n) {
        if (n <= 0) return "";
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    @Test
    void e18() {
        log.info("mstery1(2, 25) : {}", mystery1(2, 25));
        log.info("mstery1(3, 11) : {}", mystery1(3, 11));
        log.info("mstery2(2, 25) : {}", mystery2(2, 25));
        log.info("mstery2(3, 11) : {}", mystery2(3, 11));
    }

    private int mystery1(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery1(a + a, b / 2);
        return mystery1(a + a, b / 2) + a;
    }

    private int mystery2(int a, int b) {
        if (b == 0) return 1;
        if (b % 2 == 0) return mystery2(a * a, b / 2);
        return mystery2(a * a, b / 2) * a;
    }

    @Test
    void e19() {
        for (int N = 0; N < 100; N++)
            System.out.println(N + " " + F(N));
    }

    @Test
    void e19enhanced() {
        for (int N = 0; N < 100; N++)
            System.out.println(N + " " + enhancedF(N));
    }

    private long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N - 1) + F(N - 2);
    }

    private long enhancedF(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;

        long nminus1 = 1;
        long nminus2 = 0;
        long temp;

        for (int i = 0; i < N - 1; i++) {
            temp = nminus2;
            nminus2 = nminus1;
            nminus1 = temp + nminus2;
        }

        return nminus1;
    }

    @Test
    void e20() {
        // TODO study Stirling's approximation
    }

    @Test
    void e21() {

        String name = "aaa";
        int dividend = 10;
        int divisor = 3;

        printScore(name, dividend, divisor);
    }

    private void printScore(String name, int dividend, int divisor) {
        double quotient = (double) dividend / divisor;
        System.out.printf("%s\t %d\t %d\t %.3f%n", name, dividend, divisor, quotient);
    }

    @Test
    void e22() {
        int[] a = {1, 3, 5, 6, 8, 10, 28, 39, 49, 59};
        rank(1, a);
    }

    private int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1, 0);
    }

    private int rank(int key, int[] a, int lo, int hi, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.printf("lo: %d, hi: %d%n", lo, hi);
        // Index of key in a[], if present, is not smaller than lo and not larger than hi.
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1, ++depth);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, ++depth);
        else return mid;
    }

    @Test
    void e24() {
        gcd(1111111, 1234567);
    }

    private int gcd(int p, int q) {
        System.out.printf("p: %d, q: %d%n", p, q);
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

}
