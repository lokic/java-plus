package com.github.lokic.javaplus.func.sneakythrows;

import com.github.lokic.javaplus.func.throwable.ThrowConsumer1;
import lombok.SneakyThrows;

import java.util.function.Consumer;

@FunctionalInterface
public interface SneakyThrowConsumer1<T> extends Consumer<T>, ThrowConsumer1<T> {


    @SneakyThrows
    @Override
    default void accept(T t) {
        throwableAccept(t);
    }


    static <T> SneakyThrowConsumer1<T> cast(ThrowConsumer1<T> throwConsumer1) {
        return throwConsumer1::throwableAccept;
    }
}
