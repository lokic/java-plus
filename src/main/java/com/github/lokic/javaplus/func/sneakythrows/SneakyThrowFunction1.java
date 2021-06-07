package com.github.lokic.javaplus.func.sneakythrows;

import lombok.SneakyThrows;

import java.util.function.Function;

@FunctionalInterface
public interface SneakyThrowFunction1<T, R> extends Function<T, R> {

    R throwableApply(T t) throws Throwable;

    @SneakyThrows
    @Override
    default R apply(T t) {
        return throwableApply(t);
    }

}