package org.srwcrw.random;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//@OutputTimeUnit(TimeUnit.NANOSECONDS)
//@BenchmarkMode(Mode.AverageTime)
//@OperationsPerInvocation(10 * 1000 * 1000)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
public class Main {
    private static final int numberOfRandomStrings = 100 * 1000;

    public static void main(String[] args) throws IOException, RunnerException {
        Options opt = new OptionsBuilder()
                .include(Main.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();

        runBenchmark();
    }

//    @Benchmark
//    private static void runBenchmark(Map<String, String> map) {
    private static void runBenchmark() {
        Map<String, String> map = generateMapOfRandomStrings();

        Stream<Map.Entry<String, String>> stream = map.entrySet().stream();

        List<Map.Entry<String, String>> resultList = stream.filter(stringStringEntry -> stringStringEntry.getKey().equals("A")).collect(Collectors.toList());
    }

    private static Map<String, String> generateMapOfRandomStrings() {
        Map<String, String> map = new HashMap<>();

        IntStream.range(1, numberOfRandomStrings).forEach(counter -> {
            String key = UUID.randomUUID().toString();
            String value = UUID.randomUUID().toString();
            map.put(key, value);
        });

        return map;
    }
}
