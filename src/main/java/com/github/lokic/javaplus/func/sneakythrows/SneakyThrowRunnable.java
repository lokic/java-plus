package com.github.lokic.javaplus.func.sneakythrows;

import com.github.lokic.javaplus.func.throwable.ThrowRunnable;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowRunnable extends Runnable, ThrowRunnable, SneakyThrow {

    @SneakyThrows
    @Override
    default void run() {
        throwableRun();
    }

}