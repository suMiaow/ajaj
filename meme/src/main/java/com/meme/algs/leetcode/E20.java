package com.meme.algs.leetcode;

import java.util.LinkedList;
import java.util.Stack;

public class E20 {

    public static boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                stack.offerLast('}');
            } else if (c == '[') {
                stack.offerLast(']');
            } else if (c == '(') {
                stack.offerLast(')');
            } else if (c == '}' || c == ']' || c == ')') {
                Character right = stack.pollLast();
                if (right == null || !right.equals(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("{[a(a)]}"));
        System.out.println(isValid("]"));
    }
}
