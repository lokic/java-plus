package com.github.lokic.javaplus.func.sneakythrows;

import lombok.SneakyThrows;

import java.util.function.Consumer;

@FunctionalInterface
public interface SneakyThrowConsumer1<T> extends Consumer<T> {

    void throwableAccept(T t) throws Exception;

    @SneakyThrows
    @Override
    default void accept(T t) {
        throwableAccept(t);
    }
}
