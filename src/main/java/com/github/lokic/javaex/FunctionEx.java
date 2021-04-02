package com.github.lokic.javaex;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionEx {

    /**
     * 在某些遍历的场景，可以通过该方法，在 {@code biFunction} 中拿到遍历的下标
     *
     * <pre>{@code
     *     List(...)
     *      .stream()
     *      .map(FunctionEx.mapWithIndex((index, item) -> {
     *          ...
     *      })
     * }</pre>
     *
     * @param biFunction
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Function<T, R> mapWithIndex(BiFunction<Integer, T, R> biFunction) {
        AtomicInteger i = new AtomicInteger();
        return t -> biFunction.apply(i.getAndIncrement(), t);
    }
}
