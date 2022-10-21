package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.consumer.Consumer4;
import com.github.lokic.javaplus.tuple.Tuple4;

import java.util.function.Consumer;

@FunctionalInterface
public interface TupleConsumer4<T1, T2, T3, T4> extends Consumer<Tuple4<T1, T2, T3, T4>>, Consumer4<T1, T2, T3, T4> {

    @Override
    default void accept(Tuple4<T1, T2, T3, T4> tuple4) {
        accept(tuple4.getT1(), tuple4.getT2(), tuple4.getT3(), tuple4.getT4());
    }

}
