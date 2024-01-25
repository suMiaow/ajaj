package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.initNode;

public class E142 {

    public static ListNode detectCycle(ListNode head) {

        ListNode tortoise = head;
        ListNode hare = head;

        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;

            if (tortoise == hare) {
                tortoise = head;
                while (tortoise != hare) {
                    tortoise = tortoise.next;
                    hare = hare.next;
                }
                return tortoise;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode list = initNode(1, 2, 3, 4, 5);

        list.next.next.next.next.next = list;

        System.out.println(detectCycle(list).val);
    }
}
