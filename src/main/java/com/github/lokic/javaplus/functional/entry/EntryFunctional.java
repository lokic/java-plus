package com.github.lokic.javaplus.functional.entry;

import com.github.lokic.javaplus.functional.consumer.Consumer2;
import com.github.lokic.javaplus.functional.function.Function2;
import com.github.lokic.javaplus.functional.function.Function3;
import com.github.lokic.javaplus.functional.function.Function4;
import com.github.lokic.javaplus.functional.predicate.Predicate2;
import com.github.lokic.javaplus.tuple.Tuple2;
import com.github.lokic.javaplus.tuple.Tuple3;

public interface EntryFunctional {

    static <K, V, R> EntryFunction<K, V, R> function(Function2<K, V, R> function2) {
        return function2::apply;
    }

    static <T1, T2, V, R> EntryFunction<Tuple2<T1, T2>, V, R> function(Function3<T1, T2, V, R> function3) {
        return (t, v) -> function3.apply(t.getT1(), t.getT2(), v);
    }

    static <T1, T2, T3, V, R> EntryFunction<Tuple3<T1, T2, T3>, V, R> function(Function4<T1, T2, T3, V, R> function4) {
        return (t, v) -> function4.apply(t.getT1(), t.getT2(), t.getT3(), v);
    }

    static <K, V> EntryConsumer<K, V> consumer(Consumer2<K, V> consumer2) {
        return consumer2::accept;
    }

    static <K, V> EntryPredicate<K, V> predicate(Predicate2<K, V> predicate2) {
        return predicate2::test;
    }
}
