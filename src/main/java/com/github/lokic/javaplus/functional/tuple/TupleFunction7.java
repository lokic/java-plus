package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.Function7;
import com.github.lokic.javaplus.tuple.Tuple7;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction7<T1, T2, T3, T4, T5, T6, T7, R> extends Function<Tuple7<T1, T2, T3, T4, T5, T6, T7>, R>, Function7<T1, T2, T3, T4, T5, T6, T7, R> {

    @Override
    default R apply(Tuple7<T1, T2, T3, T4, T5, T6, T7> t) {
        return apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7());
    }

}
