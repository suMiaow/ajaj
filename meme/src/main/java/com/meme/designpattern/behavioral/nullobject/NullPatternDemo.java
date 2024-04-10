package com.meme.designpattern.behavioral.nullobject;

public class NullPatternDemo {

    public static void main(String[] args) {
        System.out.println(CustomerFactory.getCustomer("Rob").getName());
        System.out.println(CustomerFactory.getCustomer("Bob").getName());
        System.out.println(CustomerFactory.getCustomer("Julie").getName());
        System.out.println(CustomerFactory.getCustomer("Laura").getName());


    }
}
