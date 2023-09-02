package com.meme.algs;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer> {

    private int size = 0;
    private int capacity = 8;
    private int[] array;

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }

    public void add(int value) {
        add(size, value);
    }

    public void add(int index, int element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (index != size) {
            expandArray();
            System.arraycopy(array, index, array, index + 1, size - index);
        } else {
            expandArray();
        }
        array[index] = element;
        ++size;
    }

    public int remove(int index) {
        int removed = array[index];
        System.arraycopy(array, index + 1, array, index, size-- - index - 1);
        return removed;
    }

    private void expandArray() {

        if (size == 0) {
            array = new int[capacity];
        } else if (size >= capacity) {
            capacity += capacity >> 1;
            int[] expandedArray = new int[capacity];
            System.arraycopy(array, 0, expandedArray, 0, size);
            array = expandedArray;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            str.append(array[i]);
            if (i < size - 1) {
                str.append(", ");
            }
        }
        return str.append("]").toString();
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.add(2);
        dynamicArray.add(8);
        dynamicArray.add(6843);
        dynamicArray.add(32);
        dynamicArray.add(2);
        dynamicArray.add(32);
        dynamicArray.add(4);
        dynamicArray.add(5);
        dynamicArray.add(6);
        dynamicArray.add(9);
        dynamicArray.add(19);
        dynamicArray.add(2);
        dynamicArray.add(78);

        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size());
        int remove = dynamicArray.remove(dynamicArray.size() - 1);
        System.out.println(remove);
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size());


    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[currentIndex++];
            }
        };
    }

    public IntStream stream() {
        return IntStream.of(Arrays.copyOfRange(array, 0, size));
    }
}

