package com.github.lokic.javaex.func.function;

@FunctionalInterface
public interface Function2<T1, T2, R> {
    R apply(T1 t1, T2 t2);
}
