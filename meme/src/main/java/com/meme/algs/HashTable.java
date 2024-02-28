package com.meme.algs;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class HashTable {

    static class Entry {
        int hash;
        Object key;
        Object value;
        Entry next;

        public Entry(int hash, Object key, Object value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    Entry[] table = new Entry[16];
    int size;
    float loadFactor = 0.75f;
    int threshold = (int) (loadFactor * table.length);

    Object get(int hash, Object key) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            return null;
        }
        Entry entry = table[index];
        while (entry != null && !Objects.equals(entry.key, key)) {
            entry = entry.next;
        }
        return entry;
    }

    public Object get(Object key) {
        return get(hash(key), key);
    }

    public void put(Object key, Object value) {
        put(hash(key), key, value);
    }

    void put(int hash, Object key, Object value) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            table[index] = new Entry(hash, key, value);
        } else {
            Entry entry = table[index];
            while (true) {
                if (Objects.equals(entry.key, key)) {
                    entry.value = value;
                    return;
                } else if (entry.next == null) {
                    break;
                }
                entry = entry.next;
            }
            entry.next = new Entry(hash, key, value);
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }

    private void resize() {
        Entry[] newTable = new Entry[table.length << 1];

        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];
            Entry a = null;
            Entry b = null;
            Entry aHead = null;
            Entry bHead = null;
            while (entry != null) {
                if ((entry.hash & table.length) == 0) {
                    if (a == null) {
                        aHead = a = entry;
                    } else {
                        a = a.next = entry;
                    }
                } else {
                    if (b == null) {
                        bHead = b = entry;
                    } else {
                        b = b.next = entry;
                    }
                }
                entry = entry.next;
            }
            if (a != null) {
                a.next = null;
                newTable[i] = aHead;
            }
            if (b != null) {
                b.next = null;
                newTable[i + table.length] = bHead;
            }
        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    Object remove(int hash, Object key) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            return null;
        }
        Entry prev = null;
        Entry entry = table[index];
        while (entry != null) {
            if (Objects.equals(entry.key, key)) {

                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                entry.next = null;
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }

        return null;
    }

    public void remove(Object key) {
        remove(hash(key), key);
    }

    private static  int hash(Object key) {
        if (key instanceof String k) {
            return Hashing.murmur3_32().hashString(k, StandardCharsets.UTF_8).asInt();
        }
        return key.hashCode();
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        hashTable.put(1, "zhang", "张三");
        hashTable.put(17, "li", "李四");
        hashTable.put(2, "wang", "王五");
        System.out.println(hashTable);
    }
}
