package com.meme.jvm;

public class Demo3_2 {
    public static void main(String[] args) throws ClassNotFoundException {
        int a = 10;
        int b = a++ + ++a + a--;
        System.out.println(a);
        System.out.println(b);
        Class<?> aClass = Demo3_2.class.getClassLoader().loadClass("com.meme.jvm.StringTableDemo");
        System.out.println(aClass.getSimpleName());
    }
}
