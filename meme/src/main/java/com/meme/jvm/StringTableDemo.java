package com.meme.jvm;

// -Xmx10m -XX:-UseGCOverheadLimit
public class StringTableDemo {
    public static void main(String[] args) {

        try {
            for (int i = 0; i < 100000; i++) {
                String.valueOf(i).intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
    }
}
