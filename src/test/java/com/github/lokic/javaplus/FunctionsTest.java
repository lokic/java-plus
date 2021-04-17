package com.github.lokic.javaplus;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FunctionsTest {

    @Test
    public void test_mapWithIndex() {
        Assertions.assertThat(
                Stream.of("A", "B", "C")
                        .map(Functions.mapWithIndex((index, item) -> item + " : " + index))
                        .collect(Collectors.toList()))
                .containsExactly("A : 0", "B : 1", "C : 2");
    }

}