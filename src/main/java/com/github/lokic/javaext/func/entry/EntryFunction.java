package com.github.lokic.javaext.func.entry;


import com.github.lokic.javaext.func.function.Function2;

import java.util.Map;
import java.util.function.Function;

@FunctionalInterface
public interface EntryFunction<K, V, R> extends Function<Map.Entry<K, V>, R>, Function2<K, V, R> {

    @Override
    default R apply(Map.Entry<K, V> entry) {
        return apply(entry.getKey(), entry.getValue());
    }
}

