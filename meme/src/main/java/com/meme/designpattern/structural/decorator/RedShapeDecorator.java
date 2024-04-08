package com.meme.designpattern.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        super.draw();
        setRedBorder(this.shape);
    }

    private void setRedBorder(Shape shape) {
        System.out.println("Border color: Red");
    }
}
