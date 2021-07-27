package com.github.lokic.javaplus.func.sneakythrows;

import com.github.lokic.javaplus.func.throwable.ThrowSupplier;
import lombok.SneakyThrows;

import java.util.function.Supplier;

@FunctionalInterface
public interface SneakyThrowSupplier<T> extends Supplier<T>, ThrowSupplier<T>, SneakyThrow {

    @SneakyThrows
    @Override
    default T get() {
        return throwableGet();
    }

}
