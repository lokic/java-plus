package com.github.lokic.javaplus.functional.throwable;


@FunctionalInterface
public interface ThrowsFunction1<T, R> {

    R throwableApply(T t) throws Throwable;
}