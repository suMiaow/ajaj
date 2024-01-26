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

}
