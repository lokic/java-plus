package com.github.lokic.javaplus.functional.entry;

import com.github.lokic.javaplus.functional.consumer.Consumer2;
import com.github.lokic.javaplus.functional.function.Function2;

public interface EntryFunctional {

    static <K, V, R> EntryFunction<K, V, R> cast(Function2<K, V, R> function2) {
        return function2::apply;
    }

    static <K, V> EntryConsumer<K, V> cast(Consumer2<K, V> consumer2) {
        return consumer2::accept;
    }
}
