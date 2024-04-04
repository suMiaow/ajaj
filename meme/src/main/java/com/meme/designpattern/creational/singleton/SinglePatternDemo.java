package com.meme.designpattern.creational.singleton;

public class SinglePatternDemo {
    public static void main(String[] args) {
        SingleObject instance = SingleObject.getInstance();
        instance.showMessage();
    }
}
