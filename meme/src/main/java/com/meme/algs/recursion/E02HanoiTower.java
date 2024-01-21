package com.meme.algs.recursion;

import java.util.LinkedList;

public class E02HanoiTower {

    static LinkedList<Integer> a = new LinkedList<>();
    static LinkedList<Integer> b = new LinkedList<>();
    static LinkedList<Integer> c = new LinkedList<>();

    static void init(int n) {
        for (int i = n; i > 0; i--) {
            a.addLast(i);
        }
    }

    static void move(LinkedList<Integer> src, LinkedList<Integer> dst) {
        dst.addLast(src.removeLast());
        System.out.println("-------------------------------------");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    static void move(LinkedList<Integer> src, LinkedList<Integer> middle, LinkedList<Integer> dst, int n) {
        if (n == 1) {
            move(src, dst);
        } else {
            move(src, dst, middle, n - 1);
            move(src, dst);
            move(middle, src, dst, n - 1);
        }
    }

    static void move() {
        move(a, b, c, a.size());
    }

    public static void main(String[] args) {
        init(5);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println();
        move();

    }
}
