package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.initNode;
import static com.meme.algs.leetcode.common.ListNode.print;

public class E23 {
    public static ListNode mergeKLists(ListNode[] lists) {

        ListNode preHead = new ListNode(-1);
        if (lists == null || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        } else {
            for (int i = 0; i < lists.length; i++) {
                preHead.next = mergeTwoLists(preHead.next, lists[i]);
            }
        }

        return preHead.next;
    }

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


        ListNode result = mergeKLists(new ListNode[] {
                initNode(1, 1, 2, 3),
                initNode(2, 3, 3, 9),
                initNode(1, 2, 6, 8),
        });
        print(result);
    }
}
