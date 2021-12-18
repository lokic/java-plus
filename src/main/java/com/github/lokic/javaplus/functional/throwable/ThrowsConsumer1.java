package com.github.lokic.javaplus.functional.throwable;

@FunctionalInterface
public interface ThrowsConsumer1<T> {

    void throwableAccept(T t) throws Throwable;
}
