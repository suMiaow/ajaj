package com.meme.designpattern.structural.proxy;

public class ProxyPatternDemo {

    public static void main(String[] args) {
        ProxyImage image = new ProxyImage("test_10mb.jpg");

        System.out.println("--------");
        image.display();
        System.out.println("--------");
        image.display();
    }
}
