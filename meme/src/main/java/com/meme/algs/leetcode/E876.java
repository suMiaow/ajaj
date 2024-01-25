package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.*;

public class E876 {

    public static ListNode middleNode(ListNode head) {

        ListNode p1 = head;
        ListNode p2 = head;

        while (p2 != null && p2.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        ListNode list = initNode(1, 2, 3 ,4);

        ListNode result = middleNode(list);
        System.out.println(result.val);
    }
}
