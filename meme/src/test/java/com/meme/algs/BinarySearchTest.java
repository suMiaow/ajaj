package com.meme.algs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.meme.algs.BinarySearch.*;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTest {

    @Test
    @DisplayName("binarySearchBasic found")
    void test1() {
        int[] a = {7, 13, 21, 38, 46, 81, 89, 100};
        assertEquals(0, binarySearchBasic(a, 7));
        assertEquals(1, binarySearchBasic(a, 13));
        assertEquals(2, binarySearchBasic(a, 21));
        assertEquals(3, binarySearchBasic(a, 38));
        assertEquals(4, binarySearchBasic(a, 46));
        assertEquals(5, binarySearchBasic(a, 81));
        assertEquals(6, binarySearchBasic(a, 89));
    }


    @Test
    @DisplayName("binarySearchBasic not found")
    void test2() {

        int[] a = {7, 13, 21, 38, 46, 81, 89, 100};

        assertEquals(-1, binarySearchBasic(a, 77));
        assertEquals(-1, binarySearchBasic(a, 5));
        assertEquals(-1, binarySearchBasic(a, 101));
    }


    @Test
    @DisplayName("binarySearchAlternative found")
    void test3() {
        int[] a = {7, 13, 21, 38, 46, 81, 89, 100};
        assertEquals(0, binarySearchAlternative(a, 7));
        assertEquals(1, binarySearchAlternative(a, 13));
        assertEquals(2, binarySearchAlternative(a, 21));
        assertEquals(3, binarySearchAlternative(a, 38));
        assertEquals(4, binarySearchAlternative(a, 46));
        assertEquals(5, binarySearchAlternative(a, 81));
        assertEquals(6, binarySearchAlternative(a, 89));
    }

    @Test
    @DisplayName("binarySearchAlternative not found")
    void test4() {

        int[] a = {7, 13, 21, 38, 46, 81, 89, 100};

        assertEquals(-1, binarySearchAlternative(a, 77));
        assertEquals(-1, binarySearchAlternative(a, 5));
        assertEquals(-1, binarySearchAlternative(a, 101));
    }

    @Test
    @DisplayName("binarySearch3 found")
    void test5() {
        int[] a = {7, 13, 21, 38, 46, 81, 89, 100};
        assertEquals(0, binarySearch3(a, 7));
        assertEquals(1, binarySearch3(a, 13));
        assertEquals(2, binarySearch3(a, 21));
        assertEquals(3, binarySearch3(a, 38));
        assertEquals(4, binarySearch3(a, 46));
        assertEquals(5, binarySearch3(a, 81));
        assertEquals(6, binarySearch3(a, 89));
    }

    @Test
    @DisplayName("binarySearch3 not found")
    void test6() {

        int[] a = {7, 13, 21, 38, 46, 81, 89, 100};

        assertEquals(-1, binarySearch3(a, 77));
        assertEquals(-1, binarySearch3(a, 5));
        assertEquals(-1, binarySearch3(a, 101));
    }

    @Test
    @DisplayName("binarySearchLeftMost found")
    void test7() {
        int[] a = {7, 13, 13, 13, 13, 81, 89, 100};
        assertEquals(0, binarySearchLeftMost(a, 7));
        assertEquals(1, binarySearchLeftMost(a, 13));
        assertEquals(5, binarySearchLeftMost(a, 81));
        assertEquals(6, binarySearchLeftMost(a, 89));
    }

    @Test
    @DisplayName("binarySearchRight found")
    void test8() {
        int[] a = {7, 13, 13, 13, 13, 13, 13, 100};
        assertEquals(0, binarySearchRightMost(a, 7));
        assertEquals(6, binarySearchRightMost(a, 13));
        assertEquals(7, binarySearchRightMost(a, 100));
    }


}
