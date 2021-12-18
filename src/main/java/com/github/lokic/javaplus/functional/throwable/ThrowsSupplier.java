package com.github.lokic.javaplus.functional.throwable;

@FunctionalInterface
public interface ThrowsSupplier<T> {

    T throwableGet() throws Throwable;
}
