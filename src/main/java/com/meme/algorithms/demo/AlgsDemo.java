package com.meme.algorithms.demo;

import com.meme.algorithms.utils.DrawUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlgsDemo {

    public static void main(String[] args) {

        int size = 100;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            log.info("{}", lgCeiling(i));
            result[i] = lgCeiling(i);
        }

        DrawUtils.drawArray(result, 100, 4, 700, 300);


    }
    private static int lgCeiling(int N) {
        int result = 0;
        for (int i = N; i > 1; i /= 2) {
            result += 1;
        }
        return result;
    }

}
