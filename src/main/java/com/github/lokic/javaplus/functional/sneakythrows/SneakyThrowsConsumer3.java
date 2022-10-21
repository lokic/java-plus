package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.consumer.Consumer3;
import com.github.lokic.javaplus.functional.throwable.ThrowsConsumer3;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsConsumer3<T1, T2, T3> extends Consumer3<T1, T2, T3>, ThrowsConsumer3<T1, T2, T3> {

    @SneakyThrows
    @Override
    default void accept(T1 t1, T2 t2, T3 t3) {
        throwableAccept(t1, t2, t3);
    }

}
