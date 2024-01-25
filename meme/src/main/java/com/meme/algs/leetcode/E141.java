package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.*;

public class E141 {

    public static boolean hasCycle(ListNode head) {

        ListNode tortoise = head;
        ListNode hare = head;

        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;

            if (tortoise == hare) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        ListNode list = initNode(1, 1, 1, 1, 1);

//        list.next.next.next.next.next = list.next.next;

        System.out.println(hasCycle(list));
    }
}
