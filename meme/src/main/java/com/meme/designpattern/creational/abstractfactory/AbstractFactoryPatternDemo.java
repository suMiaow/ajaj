package com.meme.designpattern.creational.abstractfactory;

public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
        shapeFactory.getShape("square").draw();
        shapeFactory.getShape("rectangle").draw();
        shapeFactory.getShape("circle").draw();
        AbstractFactory colorFactory = FactoryProducer.getFactory("color");
        colorFactory.getColor("red").fill();
        colorFactory.getColor("green").fill();
        colorFactory.getColor("blue").fill();
    }
}
