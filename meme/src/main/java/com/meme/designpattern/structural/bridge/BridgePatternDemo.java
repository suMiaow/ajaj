package com.meme.designpattern.structural.bridge;

public class BridgePatternDemo {

    public static void main(String[] args) {
        Circle redCircle = new Circle(10, 100, 100, new RedCircle());
        Circle greenCircle = new Circle(10, 100, 100, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
