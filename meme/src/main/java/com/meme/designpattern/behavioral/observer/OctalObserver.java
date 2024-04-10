package com.meme.designpattern.behavioral.observer;

public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal\t: " + Integer.toOctalString(subject.getState()));
    }
}
