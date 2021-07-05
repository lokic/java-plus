package com.github.lokic.javaplus.func.throwable;

@FunctionalInterface
public interface ThrowConsumer1<T> {

    void throwableAccept(T t) throws Throwable;
}
