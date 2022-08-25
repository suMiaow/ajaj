package com.meme.jvm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// -Xmx20m -XX:+PrintGCDetails -verbose:gc
public class ReferenceDemo {

    private static final int _4MB = 4 * 1024 * 1024;

    public static void main(String[] args) {
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WeakReference<byte[]> ref = new WeakReference<>(new byte[_4MB]);
            list.add(ref);
            for (WeakReference<byte[]> w : list) {
                System.out.print(w.get() + " ");
            }
            System.out.println();
        }
        System.out.println("end...");
    }
}
