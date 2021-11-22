package com.github.lokic.javaplus.functional.entry;

import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntryFunctionalTest {

    @Test
    public void test_best_practice_ambiguous_method_call() {
        Map<Stream<String>, Stream<Integer>> ageMaps = new HashMap<>();
        ageMaps.put(Stream.of("A"), Stream.of(19));
        ageMaps.put(Stream.of("B"), Stream.of(20));
        ageMaps.put(Stream.of("C"), Stream.of(20));

        // EntryFunction
        ageMaps.entrySet().stream()
                .map(EntryFunctional.function(this::zipEntryTest))
                .collect(Collectors.toList());

        // EntryConsumer
        ageMaps.entrySet().stream()
                .forEach(EntryFunctional.consumer(this::zipEntryTest));
    }

    @Test
    public void test_function3() {
        Map<Tuple2<Integer, String>, Long> map = new LinkedHashMap<>();
        map.put(Tuple.of(1, "A"), 11L);
        map.put(Tuple.of(2, "B"), 22L);
        map.put(Tuple.of(3, "C"), 33L);

        List<String> re = map.entrySet().stream()
                .map(EntryFunctional.function((a, b, c) -> a + " " + b + " " + c))
                .collect(Collectors.toList());
        Assertions.assertThat(re)
                .containsExactly("1 A 11", "2 B 22", "3 C 33");
    }

    public <A, B> Stream<Map.Entry<A, B>> zipEntryTest(Stream<A> streamA, Stream<B> streamB) {
        return Stream.empty();
    }

}