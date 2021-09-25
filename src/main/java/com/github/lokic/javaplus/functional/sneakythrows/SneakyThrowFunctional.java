package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.*;

public interface SneakyThrowFunctional {

    static <T> SneakyThrowConsumer1<T> cast(ThrowConsumer1<T> throwConsumer1) {
        return throwConsumer1::throwableAccept;
    }

    static <T, R> SneakyThrowFunction1<T, R> cast(ThrowFunction1<T, R> throwFunction1) {
        return throwFunction1::throwableApply;
    }

    static <T1, T2, R> SneakyThrowFunction2<T1, T2, R> cast(ThrowFunction2<T1, T2, R> throwFunction2) {
        return throwFunction2::throwableApply;
    }

    static <T1, T2, T3, R> SneakyThrowFunction3<T1, T2, T3, R> cast(ThrowFunction3<T1, T2, T3, R> throwFunction3) {
        return throwFunction3::throwableApply;
    }

    static SneakyThrowRunnable cast(ThrowRunnable throwRunnable) {
        return throwRunnable::throwableRun;
    }

    static <T> SneakyThrowSupplier<T> cast(ThrowSupplier<T> throwSupplier) {
        return throwSupplier::throwableGet;
    }

}
