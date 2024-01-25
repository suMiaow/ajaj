package com.meme.algs.leetcode.common;

import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static void print(ListNode head) {

        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + "\t");
            curr = curr.next;
        }
        System.out.println();
    }

    public static ListNode initNode(int... list) {

        ListNode head = null;
        ListNode curr = null;
        for (Integer i : list) {
            ListNode newNode = new ListNode(i);
            if (head == null) {
                head = newNode;
            }
            if (curr != null) {
                curr.next = newNode;
            }
            curr = newNode;
        }
        return head;
    }
}