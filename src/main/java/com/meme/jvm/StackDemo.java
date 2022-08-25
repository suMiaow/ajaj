package com.meme.jvm;

public class StackDemo {
    public static void main(String[] args) {

        method2(1, 2);
    }

    public static int method1(int a, int b) {
        return a + b;
    }

    public static int method2(int a, int b) {
        return method1(a, b);
    }

}
