package com.github.lokic.javaplus.functional.function;

import java.util.function.Function;

@FunctionalInterface
public interface Function2<T1, T2, R> {
    R apply(T1 t1, T2 t2);

    static <T1, T2, R> Function2<T1, T2, R> $1(Function<T1, R> function) {
        return (t1, t2) -> function.apply(t1);
    }

    static <T1, T2, R> Function2<T1, T2, R> $2(Function<T2, R> function) {
        return (t1, t2) -> function.apply(t2);
    }
}
