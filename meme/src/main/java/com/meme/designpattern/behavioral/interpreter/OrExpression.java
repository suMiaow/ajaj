package com.meme.designpattern.behavioral.interpreter;

public class OrExpression implements Expression {

    private Expression left;
    private Expression right;

    public OrExpression(final Expression left, final Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(String context) {
        return left.interpret(context) || right.interpret(context);
    }
}
