package com.meme.designpattern.behavioral.strategy;

public class StrategyPatternDemo {

    public static void main(String[] args) {
        System.out.println(new Context(new OperationAdd()).executeStrategy(10, 5));
        System.out.println(new Context(new OperationSubtract()).executeStrategy(10, 5));
        System.out.println(new Context(new OperationMultiply()).executeStrategy(10, 5));
    }
}
