package com.github.lokic.javaplus.functional.entry;

import com.github.lokic.javaplus.functional.consumer.Consumer2;
import com.github.lokic.javaplus.functional.function.*;
import com.github.lokic.javaplus.functional.predicate.Predicate2;
import com.github.lokic.javaplus.tuple.*;

public interface EntryFunctional {

    static <K, V, R> EntryFunction<K, V, R> function(Function2<K, V, R> function) {
        return function::apply;
    }

    static <T1, T2, V, R> EntryFunction<Tuple2<T1, T2>, V, R> function(Function3<T1, T2, V, R> function) {
        return (t, v) -> function.apply(t.getT1(), t.getT2(), v);
    }

    static <T1, T2, T3, V, R> EntryFunction<Tuple3<T1, T2, T3>, V, R> function(Function4<T1, T2, T3, V, R> function) {
        return (t, v) -> function.apply(t.getT1(), t.getT2(), t.getT3(), v);
    }

    static <T1, T2, T3, T4, V, R> EntryFunction<Tuple4<T1, T2, T3, T4>, V, R> function(Function5<T1, T2, T3, T4, V, R> function) {
        return (t, v) -> function.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), v);
    }

    static <T1, T2, T3, T4, T5, V, R> EntryFunction<Tuple5<T1, T2, T3, T4, T5>, V, R> function(Function6<T1, T2, T3, T4, T5, V, R> function) {
        return (t, v) -> function.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), v);
    }

    static <T1, T2, T3, T4, T5, T6, V, R> EntryFunction<Tuple6<T1, T2, T3, T4, T5, T6>, V, R> function(Function7<T1, T2, T3, T4, T5, T6, V, R> function) {
        return (t, v) -> function.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), v);
    }

    static <T1, T2, T3, T4, T5, T6, T7, V, R> EntryFunction<Tuple7<T1, T2, T3, T4, T5, T6, T7>, V, R> function(Function8<T1, T2, T3, T4, T5, T6, T7, V, R> function) {
        return (t, v) -> function.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7(), v);
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, V, R> EntryFunction<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, V, R> function(Function9<T1, T2, T3, T4, T5, T6, T7, T8, V, R> function) {
        return (t, v) -> function.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7(), t.getT8(), v);
    }


    static <K, V> EntryConsumer<K, V> consumer(Consumer2<K, V> consumer2) {
        return consumer2::accept;
    }

    static <K, V> EntryPredicate<K, V> predicate(Predicate2<K, V> predicate2) {
        return predicate2::test;
    }
}
