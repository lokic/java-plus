package com.github.lokic.javaplus.functional.throwable;


@FunctionalInterface
public interface ThrowsRunnable {

    void throwableRun() throws Throwable;
}