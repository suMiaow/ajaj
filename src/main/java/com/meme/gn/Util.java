package com.meme.gn;

public class Util {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
    }

    public static void main(String[] args) {
        Pair<Integer, String> p1 = new Pair<>(1, "aa");
        Pair<Integer, String> p2 = new Pair<>(1, "bb");

        Util.<Integer, String>compare(p1, p2);
    }
}
