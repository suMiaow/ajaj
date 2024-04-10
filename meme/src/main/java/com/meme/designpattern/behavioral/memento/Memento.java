package com.meme.designpattern.behavioral.memento;

import lombok.Getter;

public class Memento {

    @Getter
    private String state;

    public Memento(String state) {
        this.state = state;
    }

}
