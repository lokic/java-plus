package com.github.lokic.javaext;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {

    /**
     * 在某些遍历的场景，可以通过该方法，在 {@code biFunction} 中拿到遍历的下标
     *
     * <pre>{@code
     *     List(...)
     *      .stream()
     *      .map(Functions.mapWithIndex((index, item) -> {
     *          ...
     *      })
     * }</pre>
     */
    public static <T, R> Function<T, R> mapWithIndex(BiFunction<Integer, T, R> biFunction) {
        AtomicInteger i = new AtomicInteger();
        return t -> biFunction.apply(i.getAndIncrement(), t);
    }
}
