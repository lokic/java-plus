package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowFunction1;
import lombok.SneakyThrows;

import java.util.function.Function;

@FunctionalInterface
public interface SneakyThrowFunction1<T, R> extends Function<T, R>, ThrowFunction1<T, R> {

    @SneakyThrows
    @Override
    default R apply(T t) {
        return throwableApply(t);
    }

}