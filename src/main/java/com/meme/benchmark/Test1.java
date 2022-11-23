package com.meme.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
public class Test1 {

    static int[] ARRAY = new int[1000_000_00];
    static {
        Arrays.fill(ARRAY, 1);
    }

    @Benchmark
    public void c() {
        // do nothing
    }


}
