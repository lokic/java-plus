package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsFunction1;
import lombok.SneakyThrows;

import java.util.function.Function;

@FunctionalInterface
public interface SneakyThrowsFunction1<T, R> extends Function<T, R>, ThrowsFunction1<T, R> {

    @SneakyThrows
    @Override
    default R apply(T t) {
        return throwableApply(t);
    }

}