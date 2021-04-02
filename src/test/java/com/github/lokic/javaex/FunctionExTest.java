package com.github.lokic.javaex;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FunctionExTest {

    @Test
    public void test_mapWithIndex() {
        Assertions.assertThat(
                Stream.of("A", "B", "C")
                        .map(FunctionEx.mapWithIndex((index, item) -> item + " : " + index))
                        .collect(Collectors.toList()))
                .containsExactly("A : 0", "B : 1", "C : 2");
    }

}