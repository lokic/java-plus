package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.Function8;
import com.github.lokic.javaplus.tuple.Tuple8;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction8<T1, T2, T3, T4, T5, T6, T7, T8, R> extends Function<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, R>, Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> {

    @Override
    default R apply(Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> t) {
        return apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7(), t.getT8());
    }

}
