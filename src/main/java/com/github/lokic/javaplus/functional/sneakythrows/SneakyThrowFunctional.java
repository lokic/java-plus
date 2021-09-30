package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.*;

public interface SneakyThrowFunctional {


    static <T> SneakyThrowConsumer1<T> consumer(ThrowConsumer1<T> throwConsumer1) {
        return throwConsumer1::throwableAccept;
    }


    static <T, R> SneakyThrowFunction1<T, R> function(ThrowFunction1<T, R> throwFunction1) {
        return throwFunction1::throwableApply;
    }

    static <T1, T2, R> SneakyThrowFunction2<T1, T2, R> function(ThrowFunction2<T1, T2, R> throwFunction2) {
        return throwFunction2::throwableApply;
    }

    static <T1, T2, T3, R> SneakyThrowFunction3<T1, T2, T3, R> function(ThrowFunction3<T1, T2, T3, R> throwFunction3) {
        return throwFunction3::throwableApply;
    }


    static SneakyThrowRunnable runnable(ThrowRunnable throwRunnable) {
        return throwRunnable::throwableRun;
    }


    static <T> SneakyThrowSupplier<T> supplier(ThrowSupplier<T> throwSupplier) {
        return throwSupplier::throwableGet;
    }
}
