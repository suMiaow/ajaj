package com.meme.algs;

import java.util.LinkedList;

public class InfixToSuffix {

    static String infixToSuffix(String exp) {
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            switch (c) {
                case '+', '-', '*', '/' -> {
                    if (stack.isEmpty()) {
                        stack.push(c);
                    } else {
                        while (!stack.isEmpty() && priority(stack.peek()) >= priority(c)) {
                            sb.append(stack.pop());
                        }
                        stack.push(c);
                    }
                }
                case '(' -> stack.push(c);
                case ')' -> {
                    char cInS;
                    while (!stack.isEmpty() && (cInS = stack.pop()) != '(') {
                        sb.append(cInS);
                    }
                }
                default -> sb.append(c);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    static int priority(char c) {
        return switch (c) {
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            case '(' -> 0;
            default -> throw new IllegalArgumentException();
        };
    }

    public static void main(String[] args) {
        System.out.println(infixToSuffix("a+b*c-d*e"));
        System.out.println(infixToSuffix("a+b*(c-d)*e"));
    }
}
