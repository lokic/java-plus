package com.github.lokic.javaplus.functional.function;

import java.util.function.Function;

@FunctionalInterface
public interface Function7<T1, T2, T3, T4, T5, T6, T7, R> {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);

    static <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> $1(Function<T1, R> function) {
        return (t1, t2, t3, t4, t5, t6, t7) -> function.apply(t1);
    }

    static <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> $2(Function<T2, R> function) {
        return (t1, t2, t3, t4, t5, t6, t7) -> function.apply(t2);
    }

    static <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> $3(Function<T3, R> function) {
        return (t1, t2, t3, t4, t5, t6, t7) -> function.apply(t3);
    }

    static <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> $4(Function<T4, R> function) {
        return (t1, t2, t3, t4, t5, t6, t7) -> function.apply(t4);
    }

    static <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> $5(Function<T5, R> function) {
        return (t1, t2, t3, t4, t5, t6, t7) -> function.apply(t5);
    }

    static <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> $6(Function<T6, R> function) {
        return (t1, t2, t3, t4, t5, t6, t7) -> function.apply(t6);
    }

    static <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> $7(Function<T7, R> function) {
        return (t1, t2, t3, t4, t5, t6, t7) -> function.apply(t7);
    }

}
