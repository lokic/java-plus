package com.github.lokic.javaplus.functional.function;

import java.util.function.Function;

@FunctionalInterface
public interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);

    static <T1, T2, T3, R> Function3<T1, T2, T3, R> $1(Function<T1, R> function) {
        return (t1, t2, t3) -> function.apply(t1);
    }

    static <T1, T2, T3, R> Function3<T1, T2, T3, R> $2(Function<T2, R> function) {
        return (t1, t2, t3) -> function.apply(t2);
    }

    static <T1, T2, T3, R> Function3<T1, T2, T3, R> $3(Function<T3, R> function) {
        return (t1, t2, t3) -> function.apply(t3);
    }
}
