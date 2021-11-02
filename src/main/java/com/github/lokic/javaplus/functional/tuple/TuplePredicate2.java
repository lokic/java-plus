package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.predicate.Predicate2;
import com.github.lokic.javaplus.tuple.Tuple2;

import java.util.function.Predicate;

@FunctionalInterface
public interface TuplePredicate2<T1, T2> extends Predicate<Tuple2<T1, T2>>, Predicate2<T1, T2> {

    @Override
    default boolean test(Tuple2<T1, T2> tuple2) {
        return test(tuple2.getT1(), tuple2.getT2());
    }
}
