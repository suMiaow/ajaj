package com.meme.designpattern.structural.facade;

public class Square implements Shape {


    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
