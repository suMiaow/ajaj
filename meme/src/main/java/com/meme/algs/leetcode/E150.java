package com.meme.algs.leetcode;

import java.util.LinkedList;

public class E150 {

    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            if ("+".equals(token)) {
                stack.offerLast(stack.pollLast() + stack.pollLast());
            } else if ("-".equals(token)) {
                Integer substractor = stack.pollLast();
                Integer substracted = stack.pollLast();
                stack.offerLast(substracted - substractor);
            } else if ("*".equals(token)) {
                stack.offerLast(stack.pollLast() * stack.pollLast());
            } else if ("/".equals(token)) {
                Integer divider = stack.pollLast();
                Integer divided = stack.pollLast();
                stack.offerLast(divided / divider);
            } else {
                stack.offerLast(Integer.parseInt(token));
            }
        }
        return stack.pollFirst();
    }


    public static void main(String[] args) {
//        System.out.println(evalRPN(new String[] {"2","1","+","3","*"}));
//        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(evalRPN(new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
//        System.out.println(evalRPN(new String[] {}));
    }
}
