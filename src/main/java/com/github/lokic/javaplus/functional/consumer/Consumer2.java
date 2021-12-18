package com.github.lokic.javaplus.functional.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface Consumer2<T1, T2> {
    void accept(T1 t1, T2 t2);

    static <T1, T2> Consumer2<T1, T2> $1(Consumer<T1> consumer) {
        return (t1, t2) -> consumer.accept(t1);
    }

    static <T1, T2> Consumer2<T1, T2> $2(Consumer<T2> consumer) {
        return (t1, t2) -> consumer.accept(t2);
    }
}
