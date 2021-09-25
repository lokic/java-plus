package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.Function4;
import com.github.lokic.javaplus.tuple.Tuple4;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction4<T1, T2, T3, T4, R> extends Function<Tuple4<T1, T2, T3, T4>, R>, Function4<T1, T2, T3, T4, R> {

    @Override
    default R apply(Tuple4<T1, T2, T3, T4> tuple4) {
        return apply(tuple4.getT1(), tuple4.getT2(), tuple4.getT3(), tuple4.getT4());
    }

}
