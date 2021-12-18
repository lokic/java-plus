package com.github.lokic.javaplus;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoizedTest {

    @Test
    public void of_stream() {
        Supplier<Stream<Integer>> streamSupplier = Memoized.of(() -> Stream.of(1, 2, 3));

        List<Integer> first = streamSupplier.get().collect(Collectors.toList());
        Assertions.assertThat(first)
                .containsExactly(1, 2, 3);

        List<Integer> second = streamSupplier.get().collect(Collectors.toList());
        Assertions.assertThat(second)
                .containsExactly(1, 2, 3);
    }
}