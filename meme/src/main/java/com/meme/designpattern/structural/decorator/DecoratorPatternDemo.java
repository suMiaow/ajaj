package com.meme.designpattern.structural.decorator;

public class DecoratorPatternDemo {

    public static void main(String[] args) {
        Circle circle = new Circle();

        RedShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        RedShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("-- circle");
        circle.draw();

        System.out.println("-- redCircle");
        redCircle.draw();

        System.out.println("-- redRectangle");
        redRectangle.draw();

    }
}
