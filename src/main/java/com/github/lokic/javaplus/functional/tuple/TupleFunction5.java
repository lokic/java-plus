package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.Function5;
import com.github.lokic.javaplus.tuple.Tuple5;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction5<T1, T2, T3, T4, T5, R> extends Function<Tuple5<T1, T2, T3, T4, T5>, R>, Function5<T1, T2, T3, T4, T5, R> {

    @Override
    default R apply(Tuple5<T1, T2, T3, T4, T5> tuple5) {
        return apply(tuple5.getT1(), tuple5.getT2(), tuple5.getT3(), tuple5.getT4(), tuple5.getT5());
    }

}
