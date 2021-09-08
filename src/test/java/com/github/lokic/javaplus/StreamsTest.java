package com.github.lokic.javaplus;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Stream;

import static com.github.lokic.javaplus.Streams.Fors.For;
import static com.github.lokic.javaplus.Streams.Fors.Yield;


public class StreamsTest {

    @Test
    public void test_For() {
        Stream<String> r = Stream.of("1")
                .flatMap(For(t1 -> Stream.of("2", "b", "B")))
                .flatMap(For((t1, t2) -> Stream.of("3", "c")))
                .flatMap(For((t1, t2, t3) -> Stream.of("4")))
                .map(Yield((t1, t2, t3, t4) -> t1 + t2 + t3 + t4));
        Assert.assertEquals(Lists.newArrayList("1234", "12c4", "1b34", "1bc4", "1B34", "1Bc4"), r.collect(java.util.stream.Collectors.toList()));
    }

}