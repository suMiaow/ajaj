package com.meme.juc;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafeField.get(null);
//        String




    }
}
