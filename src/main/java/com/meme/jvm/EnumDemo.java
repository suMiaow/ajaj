package com.meme.jvm;

import lombok.Getter;

@Getter
public enum EnumDemo {

    E1("a1", "b1"),
    ;

    private final String a;
    private final String b;

    EnumDemo(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) {
        EnumDemo.E1.ordinal();
    }

}
