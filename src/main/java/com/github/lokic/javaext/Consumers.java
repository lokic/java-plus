package com.github.lokic.javaext;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class Consumers {

    /**
     * 在遍历的场景，可以通过该方法，在 {@code biConsumer} 中拿到遍历的下标
     *
     * @param biConsumer
     * @param <T>
     * @return
     */
    public static <T> Consumer<T> mapWithIndex(BiConsumer<Integer, T> biConsumer) {
        AtomicInteger i = new AtomicInteger();
        return t -> biConsumer.accept(i.getAndIncrement(), t);
    }

    /**
     * {@code Consumer<T>} 转换 {@code Function<T, Runnable>}
     * <p>
     *
     * @param consumer
     * @param <T>
     * @return
     */
    public static <T> Function<T, Runnable> toRunnable(Consumer<T> consumer) {
        return x -> () -> consumer.accept(x);
    }

}
