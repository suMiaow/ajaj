package com.meme.designpattern.structural.flyweight;

import lombok.Setter;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw(){color=" + color + ", x=" + x + ", y=" + y + ", radius=" + radius + "}");
    }

    private String color;
    @Setter
    private int x;
    @Setter
    private int y;
    @Setter
    private int radius;

    public Circle(String color) {
        this.color = color;
    }


}
