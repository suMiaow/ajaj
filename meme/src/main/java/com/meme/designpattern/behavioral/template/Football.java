package com.meme.designpattern.behavioral.template;

public class Football extends Game{
    @Override
    void initialize() {
        System.out.println("Football initialized");
    }

    @Override
    void startPlay() {
        System.out.println("Football start playing");
    }

    @Override
    void endPlay() {
        System.out.println("Football end playing");
    }
}
