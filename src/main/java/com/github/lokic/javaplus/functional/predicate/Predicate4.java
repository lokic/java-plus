package com.github.lokic.javaplus.functional.predicate;


import java.util.function.Predicate;

@FunctionalInterface
public interface Predicate4<T1, T2, T3, T4> {
    boolean test(T1 t1, T2 t2, T3 t3, T4 t4);

    static <T1, T2, T3, T4> Predicate4<T1, T2, T3, T4> $1(Predicate<T1> predicate) {
        return (t1, t2, t3, t4) -> predicate.test(t1);
    }

    static <T1, T2, T3, T4> Predicate4<T1, T2, T3, T4> $2(Predicate<T2> predicate) {
        return (t1, t2, t3, t4) -> predicate.test(t2);
    }

    static <T1, T2, T3, T4> Predicate4<T1, T2, T3, T4> $3(Predicate<T3> predicate) {
        return (t1, t2, t3, t4) -> predicate.test(t3);
    }

    static <T1, T2, T3, T4> Predicate4<T1, T2, T3, T4> $4(Predicate<T4> predicate) {
        return (t1, t2, t3, t4) -> predicate.test(t4);
    }
}
