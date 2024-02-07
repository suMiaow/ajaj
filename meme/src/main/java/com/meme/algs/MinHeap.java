package com.meme.algs;

import java.util.Arrays;

public class MinHeap {

    int[] array;
    int size;

    public MinHeap(int capacity) {
        this.array = new int[capacity];
    }

    public MinHeap(int[] array) {
        int[] dest = new int[array.length * 2];
        System.arraycopy(array, 0, dest, 0, array.length);
        this.array = dest;
        this.size = array.length;
        heapify();
    }

    private void heapify() {
        int maxp = size / 2 - 1;
        for (int i = maxp; i >= 0; i--) {
            down(i);
        }
    }

    public static void heapify(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            down(array, array.length, i);
        }
    }

    private static void down(int[] array, int size, int p) {
        int maxp = size / 2 - 1;
        if (p > maxp) {
            return;
        }
        do {
            int l = p * 2 + 1;
            int r = p * 2 + 2;
            int min = p;
            if (r < size) {
                if (array[l] < array[p] && array[l] <= array[r]) {
                    min = l;
                } else if (array[r] < array[p] && array[r] <= array[l]) {
                    min = r;
                }
            } else {
                if (array[l] < array[p]) {
                    min = l;
                }
            }
            if (min != p) {
                swap(array, p, min);
                p = min;
            } else {
                break;
            }
        } while (p <= maxp);

    }

    private void down(int p) {
        down(array, size, p);
    }

    private void up(int i) {

        up(array, i);
    }

    private static void up(int[] array, int i) {
        if (i <= 0) {
            return;
        }
        int p;
        do {
            p = (i - 1) / 2;
            if (array[p] > array[i]) {
                swap(array, p, i);
                i = p;
            } else {
                break;
            }
        } while (i > 0);
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int poll() {
        int result = array[0];
        swap(0, size - 1);
        size--;
        down(0);
        return result;
    }

    public int poll(int index) {
        int result = array[index];
        swap(index, size - 1);
        size--;
        down(index);
        return result;
    }

    public int peek() {
        return array[0];
    }

    public void replace(int replaced) {
        replace(replaced, 0);
    }

    public void replace(int replaced, int index) {
        array[index] = replaced;
        down(index);
    }

    public boolean offer(int offered) {
        array[size++] = offered;
        up(size - 1);
        return true;
    }

    public static void sort(int[] array) {
        heapify(array);
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            down(array, i, 0);
        }
    }

    public static void main(String[] args) {
        MinHeap maxHeap = new MinHeap(new int[]{7, 2, 83, 2, 8, 4, 91, 6, 5, 0, 100, 48, 3});
        System.out.println(Arrays.toString(maxHeap.array));
        System.out.println(maxHeap.offer(99));
        System.out.println(Arrays.toString(maxHeap.array));

        int[] array = new int[]{3, 2, 5, 1, 9, 3, 5, 21};
        sort(array);

        System.out.println(Arrays.toString(array));
    }
}
