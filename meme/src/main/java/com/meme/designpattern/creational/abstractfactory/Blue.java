package com.meme.designpattern.creational.abstractfactory;

public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue fill() method");
    }
}
