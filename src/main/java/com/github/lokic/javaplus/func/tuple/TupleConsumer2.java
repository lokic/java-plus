package com.github.lokic.javaplus.func.tuple;

import com.github.lokic.javaplus.func.consumer.Consumer2;
import com.github.lokic.javaplus.tuple.Tuple2;

import java.util.function.Consumer;

@FunctionalInterface
public interface TupleConsumer2<T1, T2> extends Consumer<Tuple2<T1, T2>>, Consumer2<T1, T2> {

    @Override
    default void accept(Tuple2<T1, T2> tuple2) {
        accept(tuple2.getT1(), tuple2.getT2());
    }

    static <T1, T2> TupleConsumer2<T1, T2> cast(Consumer2<T1, T2> consumer2) {
        return consumer2::accept;
    }
}
