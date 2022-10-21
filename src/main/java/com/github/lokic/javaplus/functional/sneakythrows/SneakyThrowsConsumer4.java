package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.consumer.Consumer4;
import com.github.lokic.javaplus.functional.throwable.ThrowsConsumer4;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsConsumer4<T1, T2, T3, T4> extends Consumer4<T1, T2, T3, T4>, ThrowsConsumer4<T1, T2, T3, T4> {

    @SneakyThrows
    @Override
    default void accept(T1 t1, T2 t2, T3 t3, T4 t4) {
        throwableAccept(t1, t2, t3, t4);
    }

}
