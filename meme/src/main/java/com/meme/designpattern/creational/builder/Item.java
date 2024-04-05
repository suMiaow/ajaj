package com.meme.designpattern.creational.builder;

public interface Item {
    String name();
    Packing packing();
    float price();

    default String description() {
        return "Item: " + name() + ", Packing: " + packing().pack() + ", Price: " + price();
    }

}
