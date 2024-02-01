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

    public static class MinHeap {
        ListNode[] array;
        int size;

        public MinHeap(int capacity) {
            array = new ListNode[capacity];
        }

        public boolean offer(ListNode node) {
            if (isFull()) {
                return false;
            }
            int child = size++;
            for (int parent = (child - 1) / 2;
                 child > 0 && array[parent].val > node.val;
                 child = parent, parent = (child - 1) / 2) {
                array[child] = array[parent];
            }

            array[child] = node;

            return true;
        }

        public ListNode poll() {
            if (isEmpty()) {
                return null;
            }

            ListNode result = array[0];

            ListNode tail = array[--size];
            int targetPriority = tail.val;
            array[size] = null;
            int parent = 0;
            for (int leftChild = 1, rightChild = 2; leftChild < size; leftChild = parent * 2 + 1, rightChild = parent * 2 + 2) {
                int minIndex = parent;
                int minVal = targetPriority;
                if (minVal > array[leftChild].val) {
                    minIndex = leftChild;
                    minVal = array[leftChild].val;
                }
                if (rightChild < size && minVal > array[rightChild].val) {
                    minIndex = rightChild;
                }
                if (parent == minIndex) {
                    break;
                } else {
                    array[parent] = array[minIndex];
                    parent = minIndex;
                }
            }
            array[parent] = tail;

            return result;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }

    }

    public static ListNode mergeKLists2(ListNode[] lists) {

        ListNode preHead = new ListNode();
        if (lists == null || lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        } else {
            MinHeap minHeap = new MinHeap(10000);
            for (ListNode list : lists) {
                if (list != null) {
                    minHeap.offer(list);
                }
            }
            ListNode tail = preHead;
            while (!minHeap.isEmpty()) {
                tail.next = minHeap.poll();
                tail = tail.next;
                if (tail.next != null) {
                    minHeap.offer(tail.next);
                }
            }

        }

        return preHead.next;
    }

    public static void main(String[] args) {


        ListNode result = mergeKLists(new ListNode[] {
                initNode(1, 1, 2, 3),
                initNode(),
                initNode(2, 3, 3, 9),
                initNode(1, 2, 6, 8),
        });
        print(result);
    }
}
