package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsRunnable;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsRunnable extends Runnable, ThrowsRunnable {

    @SneakyThrows
    @Override
    default void run() {
        throwableRun();
    }

}