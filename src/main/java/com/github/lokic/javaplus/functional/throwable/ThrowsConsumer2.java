package com.github.lokic.javaplus.functional.throwable;

@FunctionalInterface
public interface ThrowsConsumer2<T1, T2> {

    void throwableAccept(T1 t1, T2 t2) throws Throwable;
}
