package com.meme.algs;

import java.util.Arrays;

public class DisjointSet {

    int[] s;
    int[] size;

    public DisjointSet(int size) {
        this.s = new int[size];
        this.size = new int[size];
        for (int i = 0; i < size; i++) {
            s[i] = i;
            this.size[i] = 1;
        }

    }

    public int find(int x) {
        if (x == s[x]) {
            return x;
        }
        return s[x] = find(s[x]);
    }

    public void union(int x, int y) {
        if (size[x] < size[y]) {
            union(y, x);
        } else {
            s[y] = x;
            size[x] += size[y];
        }

    }

    @Override
    public String toString() {
        return "elements: " + Arrays.toString(s) + ", size: " + Arrays.toString(size);
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet(7);
        System.out.println(set);

        int x = set.find(0);
        int y = set.find(3);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }
        x = set.find(5);
        y = set.find(6);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        x = set.find(0);
        y = set.find(1);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        x = set.find(2);
        y = set.find(3);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        x = set.find(1);
        y = set.find(3);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        x = set.find(0);
        y = set.find(2);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        x = set.find(3);
        y = set.find(6);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        x = set.find(2);
        y = set.find(5);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        x = set.find(4);
        y = set.find(6);
        System.out.println("x: " + x + ", y: " + y);
        if (x != y) {
            set.union(x, y);
            System.out.println(set);
        }

        DisjointSet set2 = new DisjointSet(7);
        System.out.println(set2);
        set2.union(0, 1);
        set2.union(1, 2);
        set2.union(2, 3);
        set2.union(3, 4);
        set2.union(4, 5);
        set2.union(5, 6);
        System.out.println(set2);
        set2.find(6);
        System.out.println(set2);

    }
}
