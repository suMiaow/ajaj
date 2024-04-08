package com.meme.designpattern.behavioral.command;

import java.util.ArrayList;
import java.util.List;

public class Broker {

    private List<Order> orderList = new ArrayList<>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrder() {
        orderList.forEach(Order::execute);
        orderList.clear();
    }
}
