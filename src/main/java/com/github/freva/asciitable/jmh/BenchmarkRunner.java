package com.github.freva.asciitable.jmh;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.github.freva.asciitable.AsciiTable;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;


public class BenchmarkRunner {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
    @Fork(value = 1, warmups = 1)
    public void benchmark(Blackhole blackhole, ExecutionPlan executionPlan){
        TableData current = executionPlan.next();
        String s = AsciiTable.builder()
                   .border(AsciiTable.BASIC_ASCII)
                .header(current.header)
                .data(current.data)
                .asString();
        blackhole.consume(s);
    }
}
