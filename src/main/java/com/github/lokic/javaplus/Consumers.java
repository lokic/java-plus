package com.github.lokic.javaplus;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Consumers {

    /**
     * 在遍历的场景，可以通过该方法，在 {@code biConsumer} 中拿到遍历的下标
     */
    public static <T> Consumer<T> mapWithIndex(BiConsumer<Integer, T> biConsumer) {
        AtomicInteger i = new AtomicInteger();
        return t -> biConsumer.accept(i.getAndIncrement(), t);
    }

}
