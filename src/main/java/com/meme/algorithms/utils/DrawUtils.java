package com.meme.algorithms.utils;

import edu.princeton.cs.algs4.StdDraw;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class DrawUtils {


    public static void drawArray(int[] a, int maxX, int maxY) {
        drawArray(Arrays.stream(a).mapToDouble(value -> value).toArray(), maxX, maxY);
    }

    public static void drawArray(double[] a, int maxX, int maxY) {
        int width = maxX > maxY ? 1000 : 1000 * maxX / maxY;
        int height = maxY > maxX ? 1000 : 1000 * maxY / maxX;
        StdDraw.setCanvasSize(width, height);

        for (int i = 0; i < a.length; i++) {
            double x = 1.0 * i / a.length;
            double y = a[i] / height / 2.0;
            double rw = 0.4 / a.length;
            double rh = a[i] / height / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
