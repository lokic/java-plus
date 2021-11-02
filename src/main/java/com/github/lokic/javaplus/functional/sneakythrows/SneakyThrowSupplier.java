package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowSupplier;
import lombok.SneakyThrows;

import java.util.function.Supplier;

@FunctionalInterface
public interface SneakyThrowSupplier<T> extends Supplier<T>, ThrowSupplier<T> {

    @SneakyThrows
    @Override
    default T get() {
        return throwableGet();
    }

}
