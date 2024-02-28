package com.meme.algs;

import java.util.Arrays;

public class BTree {

    public static class Node {
        int[] keys;
        Node[] children;
        int keyNumber;
        boolean leaf = true;

        /**
         * minimum degree
         */
        int t;

        public Node(int t) { // t >= 2
            this.t = t;
            this.children = new Node[2 * t];
            this.keys = new int[2 * t - 1];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        Node get(int key) {
            int i;
            for (i = 0; i < keyNumber; i++) {
                if (key == keys[i]) {
                    return this;
                } else if (key < keys[i]) {
                    break;
                }
            }
            if (children[i] != null) {
                return children[i].get(key);
            } else {
                return null;
            }
        }

        void insertKey(int key, int index) {
            System.arraycopy(keys, index, keys, index + 1, keyNumber - index);
            keys[index] = key;
            keyNumber++;
        }

        void insertChild(Node child, int index) {

            System.arraycopy(children, index, children, index + 1, keyNumber - index);
            children[index] = child;
        }


        int removeKey(int index) {
            int temp = keys[index];
            System.arraycopy(keys, index + 1, keys, index, --keyNumber - index);
            return temp;
        }

        int removeLeftMostKey() {
            return removeKey(0);
        }

        int removeRightMostKey() {
            return removeKey(keyNumber - 1);
        }

        Node removeChild(int index) {
            Node temp = children[index];
            System.arraycopy(children, index + 1, children, index, keyNumber - index);
            return temp;
        }

        Node removeLeftMostChild() {
            return removeChild(0);
        }

        Node removeRightMostChild() {
            return removeChild(keyNumber);
        }

        Node childLeftSibling(int index) {
            return index > 0 ? children[index - 1] : null;
        }

        Node childRightSibling(int index) {
            return index == keyNumber ? null : children[index + 1];
        }

        void moveToTarget(Node target) {
            if (!leaf) {
                System.arraycopy(children, 0, target.children, target.keyNumber + 1, keyNumber + 1);
            }

            for (int i = 0; i < keyNumber; i++) {
                target.keys[target.keyNumber++] = keys[i];
            }
        }
    }

    Node root;

    int t;
    final int MIN_KEY_NUMBER;
    final int MAX_KEY_NUMBER;

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MIN_KEY_NUMBER = t - 1;
        MAX_KEY_NUMBER = t * 2 - 1;
    }

    public BTree() {
        this(2);
    }

    public boolean contains(int key) {
        return root.get(key) != null;
    }

    public void put(int key) {
        doPut(root, key, null, 0);
    }

    public void doPut(Node node, int key, Node parent, int index) {
        int i;
        for (i = 0; i < node.keyNumber; i++) {
            if (node.keys[i] == key) {
                return; // update
            } else if (node.keys[i] > key) {
                break; // insert point
            }
        }

        if (node.leaf) {
            node.insertKey(key, i);
        } else {
            doPut(node.children[i], key, node, i);
        }
        if (node.keyNumber == MAX_KEY_NUMBER) {
            split(node, parent, index);
        }
    }

    private void split(Node left, Node parent, int index) {
        if (parent == null) {
            Node newRoot = new Node(t);
            newRoot.leaf = false;
            newRoot.insertChild(left, 0);
            this.root = newRoot;
            parent = newRoot;
        }

        Node right = new Node(t);
        right.leaf = left.leaf;
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);

        if (!right.leaf) {
            System.arraycopy(left.children, t, right.children, 0, t);
        }

        right.keyNumber = t - 1;

        left.keyNumber = t - 1;

        int mid = left.keys[t - 1];
        parent.insertKey(mid, index);

        parent.insertChild(right, index + 1);

    }

    public void remove(int key) {
        doRemove(null, root, 0, key);
    }

    private void doRemove(Node parent, Node node, int index, int key) {
        int i;
        for (i = 0; i < node.keyNumber; i++) {
            if (node.keys[i] >= key) {
                break;
            }
        }

        if (node.leaf) {
            if (found(node, key, i)) {
                node.removeKey(i);
            } else { // not found
                return;
            }
        } else {
            if (found(node, key, i)) {
                Node s = node.children[i + 1];
                while (!s.leaf) {
                    s = s.children[0];
                }
                int sKey = s.keys[0];
                node.keys[i] = sKey;
                doRemove(node, node.children[i + 1], i + 1, sKey);
            } else { // not found
                doRemove(node, node.children[i], i, key);
            }
        }

        if (node.keyNumber < MIN_KEY_NUMBER) {
            balance(parent, node, index);
        }

    }

    private static boolean found(Node node, int key, int i) {
        return i < node.keyNumber && node.keys[i] == key;
    }

    private void balance(Node parent, Node node, int i) {
        if (node == root) {
            if (root.keyNumber == 0 && root.children[0] != null) {
                root = root.children[0];
            }
            return;
        }
        Node leftSibling = parent.childLeftSibling(i);
        Node rightSibling = parent.childRightSibling(i);
        if (leftSibling != null && leftSibling.keyNumber > MIN_KEY_NUMBER) {

            node.insertKey(parent.keys[i - 1], 0);
            if (!leftSibling.leaf) {
                node.insertChild(leftSibling.removeRightMostChild(), 0);
            }
            parent.keys[i - 1] = leftSibling.removeRightMostKey();
            return;
        }

        if (rightSibling != null && rightSibling.keyNumber > MIN_KEY_NUMBER) {

            node.insertKey(parent.keys[i], node.keyNumber);
            if (!rightSibling.leaf) {
                node.insertChild(rightSibling.removeLeftMostChild(), node.keyNumber);
            }
            parent.keys[i] = rightSibling.removeLeftMostKey();
            return;
        }

        if (leftSibling != null) {
            parent.removeChild(i);
            leftSibling.insertKey(parent.removeKey(i - 1), leftSibling.keyNumber);
            node.moveToTarget(leftSibling);
        } else {
            parent.removeChild(i + 1);
            node.insertKey(parent.removeKey(i), node.keyNumber);
            rightSibling.moveToTarget(node);
        }


    }
}
