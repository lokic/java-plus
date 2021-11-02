package com.github.lokic.javaplus.functional.function;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Functional {

    /**
     * {@code Consumer<T>} 转换 {@code Function<T, Runnable>}
     */
    static <T> Function<T, Runnable> runnable(Consumer<T> consumer) {
        return x -> () -> consumer.accept(x);
    }

}
