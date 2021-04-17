package com.github.lokic.javaplus.func.tuple;

import com.github.lokic.javaplus.func.consumer.Consumer3;
import com.github.lokic.javaplus.tuple.Tuple3;

import java.util.function.Consumer;

@FunctionalInterface
public interface TupleConsumer3<T1, T2, T3> extends Consumer<Tuple3<T1, T2, T3>>, Consumer3<T1, T2, T3> {

    @Override
    default void accept(Tuple3<T1, T2, T3> tuple3) {
        accept(tuple3.getT1(), tuple3.getT2(), tuple3.getT3());
    }

}
