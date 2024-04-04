package com.meme.designpattern.creational.factory;

public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        shapeFactory.getShape("Circle").draw();
        shapeFactory.getShape("Rectangle").draw();
        shapeFactory.getShape("Square").draw();
    }
}
