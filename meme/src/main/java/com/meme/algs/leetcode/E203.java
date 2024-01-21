package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

public class E203 {

    public static ListNode removeElements(ListNode head, int val) {

        ListNode sentinel = new ListNode();
        sentinel.next = head;
        ListNode temp = sentinel;

        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }

        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.initNode(new int[] {1,3,3,7,9});
        ListNode.print(head);
        ListNode nh = removeElements(head, 3);
        ListNode.print(nh);

    }
}
