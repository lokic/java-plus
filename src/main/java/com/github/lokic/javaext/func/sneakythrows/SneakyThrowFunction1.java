package com.github.lokic.javaext.func.sneakythrows;

import lombok.SneakyThrows;

import java.util.function.Function;

@FunctionalInterface
public interface SneakyThrowFunction1<T, R> extends Function<T, R> {

    R throwableApply(T t) throws Exception;

    @SneakyThrows
    @Override
    default R apply(T t) {
        return throwableApply(t);
    }

}