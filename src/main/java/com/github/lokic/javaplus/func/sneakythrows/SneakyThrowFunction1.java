package com.github.lokic.javaplus.func.sneakythrows;

import com.github.lokic.javaplus.func.throwable.ThrowFunction1;
import lombok.SneakyThrows;

import java.util.function.Function;

@FunctionalInterface
public interface SneakyThrowFunction1<T, R> extends Function<T, R>, ThrowFunction1<T, R>, SneakyThrow {

    @SneakyThrows
    @Override
    default R apply(T t) {
        return throwableApply(t);
    }

}