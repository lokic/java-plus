package com.github.lokic.javaplus.functional.throwable;

@FunctionalInterface
public interface ThrowSupplier<T> {

    T throwableGet() throws Throwable;
}
