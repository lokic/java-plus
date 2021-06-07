package com.github.lokic.javaplus.func.sneakythrows;

import lombok.SneakyThrows;

import java.util.function.Supplier;

@FunctionalInterface
public interface SneakyThrowSupplier<T> extends Supplier<T> {

    T throwableGet() throws Throwable;

    @SneakyThrows
    @Override
    default T get() {
        return throwableGet();
    }

}
