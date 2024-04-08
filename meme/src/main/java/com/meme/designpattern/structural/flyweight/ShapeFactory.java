package com.meme.designpattern.structural.flyweight;

import java.util.HashMap;

public class ShapeFactory {

    private static final HashMap<String, Shape> shapeMap = new HashMap<>();

    public static Shape getCircle(String color) {
        return shapeMap.computeIfAbsent(color, k -> {
            System.out.println("Added color: " + color);
            return new Circle(k);
        });
    }
}
