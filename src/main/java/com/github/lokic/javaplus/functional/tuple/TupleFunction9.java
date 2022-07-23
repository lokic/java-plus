package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.Function9;
import com.github.lokic.javaplus.tuple.Tuple9;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> extends Function<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R>, Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> {

    @Override
    default R apply(Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> t) {
        return apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7(), t.getT8(), t.getT9());
    }

}
