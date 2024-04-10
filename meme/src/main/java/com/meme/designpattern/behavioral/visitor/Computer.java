package com.meme.designpattern.behavioral.visitor;

public class Computer implements ComputerPart {
    ComputerPart[] computerParts;
    public Computer() {
        computerParts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor()};
    }
    @Override
    public void accept(ComputerPartVisitor visitor) {
        for (ComputerPart computerPart : computerParts) {
            computerPart.accept(visitor);
        }
        visitor.visit(this);
    }
}
