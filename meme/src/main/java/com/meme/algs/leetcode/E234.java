package com.meme.algs.leetcode;

import com.meme.algs.leetcode.common.ListNode;

import static com.meme.algs.leetcode.common.ListNode.*;

public class E234 {
    public static boolean isPalindrome(ListNode head) {

        ListNode prev1 = null;
        ListNode p1 = head;
        ListNode next1 = null;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p2 = p2.next.next;
            next1 = p1.next;
            p1.next = prev1;
            prev1 = p1;
            p1 = next1;
        }

        if (p2 != null && next1 != null) {
            next1 = next1.next;
        }

        while (prev1 != null && next1 != null) {
            if (prev1.val != next1.val) {
                return false;
            }
            prev1 = prev1.next;
            next1 = next1.next;
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(isPalindrome(initNode(1, 2, 0, 2, 1)));
        System.out.println(isPalindrome(initNode(1)));
        System.out.println(isPalindrome(initNode(1, 1)));
        System.out.println(isPalindrome(initNode(1, 9, 9, 1)));
        System.out.println(isPalindrome(initNode(1,9, 1, 1)));
        System.out.println(isPalindrome(initNode(1, 2, 0, 2, 3)));
        System.out.println(isPalindrome(initNode(1, 3, 0, 2, 1)));
    }
}
