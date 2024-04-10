package com.meme.designpattern.behavioral.state;

import lombok.Getter;
import lombok.Setter;

public class Context {

    @Getter
    @Setter
    private State state;
}
