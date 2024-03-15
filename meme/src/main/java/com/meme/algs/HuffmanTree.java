package com.meme.algs;

import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

    static class Node {
        Character ch;
        @Getter
        int freq;

        Node left;
        Node right;
        String code;

        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "ch=" + ch +
                    ", freq=" + freq +
                    '}';
        }
    }

    String str;
    Map<Character, Node> map = new HashMap<>();
    Node root;

    public HuffmanTree(String str) {
        this.str = str;
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            Node node = map.computeIfAbsent(ch, Node::new);
            node.freq++;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFreq));
        queue.addAll(map.values());
        while (queue.size() >= 2) {
            Node left = queue.poll();
            Node right = queue.poll();
            int freq = left.freq + right.freq;
            queue.offer(new Node(freq, left, right));
        }
        root = queue.poll();


        int length = dfs(root, new StringBuilder());

        System.out.println(length);
    }

    private int dfs(Node node, StringBuilder sb) {
        int sum = 0;
        if (node.isLeaf()) {
            node.code = sb.toString();
            sum = node.freq * sb.length();
        } else {
            sum += dfs(node.left, sb.append(0));
            sum += dfs(node.right, sb.append(1));
        }
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sum;
    }

    public String encode() {
       char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char ch : charArray) {
            sb.append(map.get(ch).code);
        }
        return sb.toString();
    }

    public String decode(String str) {

        char[] charArray = str.toCharArray();

        StringBuilder sb = new StringBuilder();
        Node node = root;
        int i = 0;
        do {
            if (!node.isLeaf()) {
                if (charArray[i] == '0') {
                    node = node.left;
                } else {
                    node = node.right;
                }
                i++;
            }
            if (node.isLeaf()){
                sb.append(node.ch);
                node = root;
            }
        } while (i < charArray.length);

        return sb.toString();
    }

    public static void main(String[] args) {
        HuffmanTree tree = new HuffmanTree("aabbbcccccccc");
        String encode = tree.encode();
        System.out.println(encode);
        System.out.println(tree.decode(encode));
    }

}
