package com.github.lokic.javaext.func.tuple;

import com.github.lokic.javaext.func.function.Function3;
import com.github.lokic.javaext.tuple.Tuple3;

import java.util.function.Function;

@FunctionalInterface
public interface TupleFunction3<T1, T2, T3, R> extends Function<Tuple3<T1, T2, T3>, R>, Function3<T1, T2, T3, R> {

    @Override
    default R apply(Tuple3<T1, T2, T3> tuple3) {
        return apply(tuple3.getT1(), tuple3.getT2(), tuple3.getT3());
    }
}
