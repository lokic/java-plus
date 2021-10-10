package com.github.lokic.javaplus.functional.entry;


import com.github.lokic.javaplus.functional.predicate.Predicate2;

import java.util.Map;
import java.util.function.Predicate;

@FunctionalInterface
public interface EntryPredicate<K, V> extends Predicate<Map.Entry<K, V>>, Predicate2<K, V>, EntryFunctional {
    @Override
    default boolean test(Map.Entry<K, V> entry) {
        return test(entry.getKey(), entry.getValue());
    }
}
