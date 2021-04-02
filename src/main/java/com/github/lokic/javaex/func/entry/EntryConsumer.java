package com.github.lokic.javaex.func.entry;


import com.github.lokic.javaex.func.consumer.Consumer2;

import java.util.Map;
import java.util.function.Consumer;

@FunctionalInterface
public interface EntryConsumer<K, V> extends Consumer<Map.Entry<K, V>>, Consumer2<K, V> {

    @Override
    default void accept(Map.Entry<K, V> entry) {
        accept(entry.getKey(), entry.getValue());
    }
}
