package com.github.lokic.javaex.func.tuple;

import com.github.lokic.javaex.func.function.Function2;
import com.github.lokic.javaex.tuple.Tuple2;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction2<T1, T2, R> extends Function<Tuple2<T1, T2>, R>, Function2<T1, T2, R> {

    @Override
    default R apply(Tuple2<T1, T2> tuple2) {
        return apply(tuple2.getT1(), tuple2.getT2());
    }
}
