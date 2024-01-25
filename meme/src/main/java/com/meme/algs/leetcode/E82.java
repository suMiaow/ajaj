package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.initNode;
import static com.meme.algs.leetcode.common.ListNode.print;

public class E82 {

    public static ListNode deleteDuplicates(ListNode head) {

        ListNode dummyNode = new ListNode(0, head);
        ListNode prev = dummyNode;

        while (prev.next != null && prev.next.next != null) {
            if (prev.next.val == prev.next.next.val) {
                int x = prev.next.val;
                while (prev.next != null && prev.next.val == x) {
                    prev.next = prev.next.next;
                }
            } else {
                prev = prev.next;
            }
        }

        return dummyNode.next;
    }

    public static void main(String[] args) {

        ListNode head = initNode(1, 1, 2);
        print(head);
        ListNode newHead = deleteDuplicates(head);
        print(newHead);
    }
}
