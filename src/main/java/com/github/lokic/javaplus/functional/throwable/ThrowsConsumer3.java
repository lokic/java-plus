package com.github.lokic.javaplus.functional.throwable;

@FunctionalInterface
public interface ThrowsConsumer3<T1, T2, T3> {

    void throwableAccept(T1 t1, T2 t2, T3 t3) throws Throwable;
}
