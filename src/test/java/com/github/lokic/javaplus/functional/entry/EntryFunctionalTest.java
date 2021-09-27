package com.github.lokic.javaplus.functional.entry;

import org.junit.Test;

import java.util.HashMap;
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
                .map(EntryFunctional.cast((a, b) -> (this.zipEntryTest(a, b))))
                .collect(Collectors.toList());

        // EntryConsumer
        ageMaps.entrySet().stream()
                .forEach(EntryFunctional.cast((a, b) -> {this.zipEntryTest(a, b);}));
    }

    public <A, B> Stream<Map.Entry<A, B>> zipEntryTest(Stream<A> streamA, Stream<B> streamB) {
        return Stream.empty();
    }

}