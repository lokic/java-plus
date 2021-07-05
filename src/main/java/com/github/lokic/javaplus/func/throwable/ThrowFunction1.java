package com.github.lokic.javaplus.func.throwable;


@FunctionalInterface
public interface ThrowFunction1<T, R> {

    R throwableApply(T t) throws Throwable;
}