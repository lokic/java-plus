package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsConsumer1;
import lombok.SneakyThrows;

import java.util.function.Consumer;

@FunctionalInterface
public interface SneakyThrowsConsumer1<T> extends Consumer<T>, ThrowsConsumer1<T> {

    @SneakyThrows
    @Override
    default void accept(T t) {
        throwableAccept(t);
    }

}
