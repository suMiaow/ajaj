package com.meme.designpattern.behavioral.visitor;

public interface ComputerPart {
    void accept(ComputerPartVisitor visitor);
}
