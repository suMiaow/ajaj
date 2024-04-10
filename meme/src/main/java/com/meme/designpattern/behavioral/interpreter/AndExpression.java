package com.meme.designpattern.behavioral.interpreter;

public class AndExpression implements Expression {

    private Expression left;
    private Expression right;

    public AndExpression(final Expression left, final Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean interpret(String context) {
        return left.interpret(context) && right.interpret(context);
    }
}
