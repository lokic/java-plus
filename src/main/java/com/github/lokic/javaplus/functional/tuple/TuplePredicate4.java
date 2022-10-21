package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.predicate.Predicate4;
import com.github.lokic.javaplus.tuple.Tuple4;

import java.util.function.Predicate;

@FunctionalInterface
public interface TuplePredicate4<T1, T2, T3, T4> extends Predicate<Tuple4<T1, T2, T3, T4>>, Predicate4<T1, T2, T3, T4> {

    @Override
    default boolean test(Tuple4<T1, T2, T3, T4> tuple4) {
        return test(tuple4.getT1(), tuple4.getT2(), tuple4.getT3(), tuple4.getT4());
    }
}
