package com.meme.designpattern.creational.abstractfactory;

public class Circle implements Shape {


    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
