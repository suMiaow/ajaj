package com.meme.jvm;

public class HeapDemo {
    public static void main(String[] args) {

        try {

            System.out.println("1....");
            Thread.sleep(20000);

            byte[] bytes = new byte[1024 * 1024 * 10];

            System.out.println("2....");
            Thread.sleep(20000);

            bytes = null;
            System.gc();

            System.out.println("3....");
            Thread.sleep(30000L);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
