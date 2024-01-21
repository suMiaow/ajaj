package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

public class E19 {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);

        ListNode target = dummy;
        ListNode curr = head;

        for (int i = 0; i < n; i++) {
            curr = curr.next;
        }
        while (curr != null) {
            target = target.next;
            curr = curr.next;
        }

        target.next = target.next.next;
        if (target == dummy) {
            return target.next;
        } else {
            return head;
        }

    }

    public static void main(String[] args) {
        ListNode head = ListNode.initNode(new int[]{1});
        ListNode.print(head);
        ListNode newHead = removeNthFromEnd(head, 1);
        ListNode.print(newHead);
    }
}
