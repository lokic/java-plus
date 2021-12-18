package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.*;

public interface SneakyThrowsFunctional {

    static <T> SneakyThrowsConsumer1<T> consumer(ThrowsConsumer1<T> throwsConsumer1) {
        return throwsConsumer1::throwableAccept;
    }

    static <T, R> SneakyThrowsFunction1<T, R> function(ThrowsFunction1<T, R> throwsFunction1) {
        return throwsFunction1::throwableApply;
    }

    static <T1, T2, R> SneakyThrowsFunction2<T1, T2, R> function(ThrowsFunction2<T1, T2, R> throwsFunction2) {
        return throwsFunction2::throwableApply;
    }

    static <T1, T2, T3, R> SneakyThrowsFunction3<T1, T2, T3, R> function(ThrowsFunction3<T1, T2, T3, R> throwsFunction3) {
        return throwsFunction3::throwableApply;
    }

    static SneakyThrowsRunnable runnable(ThrowsRunnable throwsRunnable) {
        return throwsRunnable::throwableRun;
    }

    static <T> SneakyThrowsSupplier<T> supplier(ThrowsSupplier<T> throwsSupplier) {
        return throwsSupplier::throwableGet;
    }
}
