package com.github.lokic.javaplus.functional.consumer;

import java.util.function.Consumer;

@FunctionalInterface
public interface Consumer3<T1, T2, T3> {
    void accept(T1 t1, T2 t2, T3 t3);


    static <T1, T2, T3> Consumer3<T1, T2, T3> $1(Consumer<T1> consumer) {
        return (t1, t2, t3) -> consumer.accept(t1);
    }

    static <T1, T2, T3> Consumer3<T1, T2, T3> $2(Consumer<T2> consumer) {
        return (t1, t2, t3) -> consumer.accept(t2);
    }

    static <T1, T2, T3> Consumer3<T1, T2, T3> $3(Consumer<T3> consumer) {
        return (t1, t2, t3) -> consumer.accept(t3);
    }
}
