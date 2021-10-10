package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.predicate.Predicate3;
import com.github.lokic.javaplus.tuple.Tuple3;

import java.util.function.Predicate;

@FunctionalInterface
public interface TuplePredicate3<T1, T2, T3> extends Predicate<Tuple3<T1, T2, T3>>, Predicate3<T1, T2, T3>, TupleFunctional {

    @Override
    default boolean test(Tuple3<T1, T2, T3> tuple3) {
        return test(tuple3.getT1(), tuple3.getT2(), tuple3.getT3());
    }
}
