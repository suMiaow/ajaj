package com.meme.designpattern.behavioral.command;

public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy() {
        System.out.println("Stock [" + name + " " + quantity + "] bought");
    }

    public void sell() {
        System.out.println("Stock [" + name + " " + quantity + "] sold");
    }
}
