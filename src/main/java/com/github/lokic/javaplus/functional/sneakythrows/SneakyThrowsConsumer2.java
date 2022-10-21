package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.consumer.Consumer2;
import com.github.lokic.javaplus.functional.throwable.ThrowsConsumer2;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsConsumer2<T1, T2> extends Consumer2<T1, T2>, ThrowsConsumer2<T1, T2> {

    @SneakyThrows
    @Override
    default void accept(T1 t1, T2 t2) {
        throwableAccept(t1, t2);
    }

}
