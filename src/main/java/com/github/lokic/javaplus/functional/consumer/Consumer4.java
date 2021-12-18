package com.github.lokic.javaplus.functional.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface Consumer4<T1, T2, T3, T4> {
    void accept(T1 t1, T2 t2, T3 t3, T4 t4);

    static <T1, T2, T3, T4> Consumer4<T1, T2, T3, T4> $1(Consumer<T1> consumer) {
        return (t1, t2, t3, t4) -> consumer.accept(t1);
    }

    static <T1, T2, T3, T4> Consumer4<T1, T2, T3, T4> $2(Consumer<T2> consumer) {
        return (t1, t2, t3, t4) -> consumer.accept(t2);
    }

    static <T1, T2, T3, T4> Consumer4<T1, T2, T3, T4> $3(Consumer<T3> consumer) {
        return (t1, t2, t3, t4) -> consumer.accept(t3);
    }

    static <T1, T2, T3, T4> Consumer4<T1, T2, T3, T4> $4(Consumer<T4> consumer) {
        return (t1, t2, t3, t4) -> consumer.accept(t4);
    }
}
