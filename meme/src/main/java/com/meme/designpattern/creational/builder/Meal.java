package com.meme.designpattern.creational.builder;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        return items.stream().map(Item::price).reduce(Float::sum).orElse(0.0f);
    }

    public void showItems() {
        items.forEach(item -> System.out.println(item.description()));
    }
}
