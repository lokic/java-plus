package com.github.lokic.javaext.func.sneakythrows;

import lombok.SneakyThrows;

import java.util.function.Supplier;

@FunctionalInterface
public interface SneakyThrowSupplier<T> extends Supplier<T> {

    T throwableGet() throws Exception;

    @SneakyThrows
    @Override
    default T get() {
        return throwableGet();
    }

}