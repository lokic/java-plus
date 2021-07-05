package com.github.lokic.javaplus.func.entry;


import com.github.lokic.javaplus.func.function.Function2;

import java.util.Map;
import java.util.function.Function;

@FunctionalInterface
public interface EntryFunction<K, V, R> extends Function<Map.Entry<K, V>, R>, Function2<K, V, R> {

    @Override
    default R apply(Map.Entry<K, V> entry) {
        return apply(entry.getKey(), entry.getValue());
    }


    static <K, V, R> EntryFunction<K, V, R> cast(Function2<K, V, R> function2) {
        return function2::apply;
    }

}

