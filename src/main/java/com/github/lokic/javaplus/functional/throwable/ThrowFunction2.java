package com.github.lokic.javaplus.functional.throwable;


@FunctionalInterface
public interface ThrowFunction2<T1, T2, R> {

    R throwableApply(T1 t1, T2 t2) throws Throwable;
}