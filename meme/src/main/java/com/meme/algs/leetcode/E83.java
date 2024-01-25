package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.*;

public class E83 {

    public static ListNode deleteDuplicates(ListNode head) {

        if (head != null) {
            ListNode curr = head;
            while (curr.next != null) {
                if (curr.val == curr.next.val) {
                    curr.next = curr.next.next;
                } else {
                    curr = curr.next;
                }
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = initNode(new int[]{1, 1, 2, 3, 5, 5, 6});
        print(head);
        ListNode newHead = deleteDuplicates(head);
        print(newHead);
    }
}
