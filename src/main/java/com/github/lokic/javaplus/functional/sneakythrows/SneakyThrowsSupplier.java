package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsSupplier;
import lombok.SneakyThrows;

import java.util.function.Supplier;

@FunctionalInterface
public interface SneakyThrowsSupplier<T> extends Supplier<T>, ThrowsSupplier<T> {

    @SneakyThrows
    @Override
    default T get() {
        return throwableGet();
    }

}
