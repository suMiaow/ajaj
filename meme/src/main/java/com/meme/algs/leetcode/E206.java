package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

public class E206 {


    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.initNode(new int[] {1, 3, 4, 7 , 9});

        ListNode.print(head);

        ListNode newHead = reverseList(head);

        ListNode.print(newHead);

    }

}
