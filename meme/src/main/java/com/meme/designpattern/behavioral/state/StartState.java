package com.meme.designpattern.behavioral.state;

public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Context is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}
