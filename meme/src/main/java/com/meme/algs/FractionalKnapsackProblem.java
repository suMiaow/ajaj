package com.meme.algs;

import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsackProblem {

    static class Item {
        int index;
        int weight;
        int value;

        public Item(int index, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "index=" + index +
                    '}';
        }

        public int unitValue() {
            return value / weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(0, 4, 24),
                new Item(1, 8, 160),
                new Item(2, 2, 4000),
                new Item(3, 6, 108),
                new Item(4, 1, 4000)
        };

        int maxValue = select(items, 10);
        System.out.println(maxValue);


    }

    static int select(Item[] items, int total) {

        Arrays.sort(items, Comparator.comparingInt(Item::unitValue).reversed());

        int result = 0;

        for (Item item : items) {
            if (total >= item.weight) {
                total -= item.weight;
                result += item.value;
            } else {
                result += total * item.unitValue();
                break;
            }
        }
        return result;
    }

}
