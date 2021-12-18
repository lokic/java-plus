package com.github.lokic.javaplus.functional.predicate;


import java.util.function.Predicate;

@FunctionalInterface
public interface Predicate2<T1, T2> {
    boolean test(T1 t1, T2 t2);

    static <T1, T2> Predicate2<T1, T2> $1(Predicate<T1> predicate) {
        return (t1, t2) -> predicate.test(t1);
    }

    static <T1, T2> Predicate2<T1, T2> $2(Predicate<T2> predicate) {
        return (t1, t2) -> predicate.test(t2);
    }
}
