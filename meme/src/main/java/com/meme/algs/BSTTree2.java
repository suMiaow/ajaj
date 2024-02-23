package com.meme.algs;

public class BSTTree2<K extends Comparable<K>, V> {
    static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode<K, V> root;

    public Object get(K key) {
        BSTNode<K, V> curr = root;

        while (curr != null) {
            if (key.equals(curr.key)) {
                return curr.value;
            } else if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        return null;
    }
}
