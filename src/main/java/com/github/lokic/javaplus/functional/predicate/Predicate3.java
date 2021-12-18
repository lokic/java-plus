package com.github.lokic.javaplus.functional.predicate;


import java.util.function.Predicate;

@FunctionalInterface
public interface Predicate3<T1, T2, T3> {
    boolean test(T1 t1, T2 t2, T3 t3);

    static <T1, T2, T3> Predicate3<T1, T2, T3> $1(Predicate<T1> predicate) {
        return (t1, t2, t3) -> predicate.test(t1);
    }

    static <T1, T2, T3> Predicate3<T1, T2, T3> $2(Predicate<T2> predicate) {
        return (t1, t2, t3) -> predicate.test(t2);
    }

    static <T1, T2, T3> Predicate3<T1, T2, T3> $3(Predicate<T3> predicate) {
        return (t1, t2, t3) -> predicate.test(t3);
    }
}
