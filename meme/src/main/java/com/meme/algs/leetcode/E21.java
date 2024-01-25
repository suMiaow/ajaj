package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.*;

public class E21 {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode preHead = new ListNode(-1);

        ListNode prev = preHead;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = list1 == null ? list2 : list1;

        return preHead.next;

    }

    public static void main(String[] args) {
        ListNode l1 = initNode(1, 1, 2, 3);
        ListNode l2 = initNode(1, 2, 2, 4);

        ListNode result = mergeTwoLists(l1, l2);
        print(result);
    }
}
