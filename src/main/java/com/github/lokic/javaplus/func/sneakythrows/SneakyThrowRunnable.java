package com.github.lokic.javaplus.func.sneakythrows;

import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowRunnable extends Runnable {

    void throwableRun() throws Exception;

    @SneakyThrows
    @Override
    default void run() {
        throwableRun();
    }
}