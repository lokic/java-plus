package com.github.lokic.javaplus.func.consumer;

@FunctionalInterface
public interface Consumer2<T1, T2> {
    void accept(T1 t1, T2 t2);
}
