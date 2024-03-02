package com.meme.algs.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AlgsUtil {

    public static <E> String iterableToString(Iterable<E> iterable) {
        StringBuilder sb = new StringBuilder("[ ");
        for (E e : iterable) {
            sb.append(e).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void swap(int[] a, int i, int j) {
        if (i != j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


    public static void merge(int[] a, int iBegin, int iEnd, int jBegin, int jEnd, int[] tmp) {
        int i = iBegin;
        int j = jBegin;
        int k = iBegin;
        while (i < iEnd && j < jEnd) {
            if (a[i] < a[j]) {
                tmp[k] = a[i];
                i++;
            } else {
                tmp[k] = a[j];
                j++;
            }
            k++;
        }
        if (i < iEnd) {
            System.arraycopy(a, i, tmp, k, iEnd - i);
        } else {
            System.arraycopy(a, j, tmp, k, jEnd - j);
        }
    }


}
