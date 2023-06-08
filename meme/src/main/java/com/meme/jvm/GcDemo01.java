package com.meme.jvm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GcDemo01 {
    public static void main(String[] args) throws IOException {
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        System.out.println(1);
        System.in.read();

        list1 = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end...");
    }
}
