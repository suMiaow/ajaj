package com.meme.designpattern.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {

    private List<Memento> momentoList = new ArrayList<>();

    public void add(Memento memento) {
        momentoList.add(memento);
    }

    public Memento get(int index) {
        return momentoList.get(index);
    }
}
