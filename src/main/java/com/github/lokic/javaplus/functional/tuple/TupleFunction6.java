package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.Function6;
import com.github.lokic.javaplus.tuple.Tuple6;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction6<T1, T2, T3, T4, T5, T6, R> extends Function<Tuple6<T1, T2, T3, T4, T5, T6>, R>, Function6<T1, T2, T3, T4, T5, T6, R> {

    @Override
    default R apply(Tuple6<T1, T2, T3, T4, T5, T6> tuple6) {
        return apply(tuple6.getT1(), tuple6.getT2(), tuple6.getT3(), tuple6.getT4(), tuple6.getT5(), tuple6.getT6());
    }

}
