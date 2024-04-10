package com.meme.designpattern.behavioral.template;

public class Cricket extends Game{
    @Override
    void initialize() {
        System.out.println("Cricket initialized");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket start playing");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket end playing");
    }
}
