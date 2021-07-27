package com.github.lokic.javaplus.func.sneakythrows;

import com.github.lokic.javaplus.func.throwable.ThrowConsumer1;
import lombok.SneakyThrows;

import java.util.function.Consumer;

@FunctionalInterface
public interface SneakyThrowConsumer1<T> extends Consumer<T>, ThrowConsumer1<T>, SneakyThrow {

    @SneakyThrows
    @Override
    default void accept(T t) {
        throwableAccept(t);
    }

}
