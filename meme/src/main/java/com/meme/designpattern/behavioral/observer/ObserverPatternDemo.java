package com.meme.designpattern.behavioral.observer;

public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Subject();

        new BinaryObserver(subject);
        new OctalObserver(subject);
        new HexObserver(subject);

        System.out.println("---- change: 15");
        subject.setState(15);
        System.out.println("---- change: 10");
        subject.setState(10);
    }
}
