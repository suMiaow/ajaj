package com.meme.algs.recursion;

public class E03PascalTriangle {

    private static int element(int i, int j) {
        if (j == 0 || i == j) {
            return 1;
        } else {
            return element(i - 1, j - 1) + element(i - 1, j);
        }
    }

    public static void main(String[] args) {
        int size = 13;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size - i; j++) {
                System.out.print("\t");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(element(i, j) + "\t\t");
            }
            System.out.println();
        }
    }
}
