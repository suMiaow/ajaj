package com.meme.designpattern.behavioral.state;

public class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Context is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "stop State";
    }
}
