package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowRunnable;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowRunnable extends Runnable, ThrowRunnable, SneakyThrowFunctional {

    @SneakyThrows
    @Override
    default void run() {
        throwableRun();
    }

}