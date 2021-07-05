package com.github.lokic.javaplus.func.throwable;

@FunctionalInterface
public interface ThrowSupplier<T> {

    T throwableGet() throws Throwable;
}
