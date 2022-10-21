package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.*;

public interface SneakyThrowsFunctional {

    static <T> SneakyThrowsConsumer1<T> consumer(ThrowsConsumer1<T> throwsConsumer1) {
        return throwsConsumer1::throwableAccept;
    }

    static <T1, T2> SneakyThrowsConsumer2<T1, T2> consumer(ThrowsConsumer2<T1, T2> throwsConsumer) {
        return throwsConsumer::throwableAccept;
    }

    static <T1, T2, T3> SneakyThrowsConsumer3<T1, T2, T3> consumer(ThrowsConsumer3<T1, T2, T3> throwsConsumer) {
        return throwsConsumer::throwableAccept;
    }

    static <T1, T2, T3, T4> SneakyThrowsConsumer4<T1, T2, T3, T4> consumer(ThrowsConsumer4<T1, T2, T3, T4> throwsConsumer) {
        return throwsConsumer::throwableAccept;
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

    static <T1, T2, T3, T4, R> SneakyThrowsFunction4<T1, T2, T3, T4, R> function(ThrowsFunction4<T1, T2, T3, T4, R> throwsFunction4) {
        return throwsFunction4::throwableApply;
    }

    static <T1, T2, T3, T4, T5, R> SneakyThrowsFunction5<T1, T2, T3, T4, T5, R> function(ThrowsFunction5<T1, T2, T3, T4, T5, R> throwsFunction5) {
        return throwsFunction5::throwableApply;
    }

    static <T1, T2, T3, T4, T5, T6, R> SneakyThrowsFunction6<T1, T2, T3, T4, T5, T6, R> function(ThrowsFunction6<T1, T2, T3, T4, T5, T6, R> throwsFunction6) {
        return throwsFunction6::throwableApply;
    }

    static <T1, T2, T3, T4, T5, T6, T7, R> SneakyThrowsFunction7<T1, T2, T3, T4, T5, T6, T7, R> function(ThrowsFunction7<T1, T2, T3, T4, T5, T6, T7, R> throwsFunction7) {
        return throwsFunction7::throwableApply;
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, R> SneakyThrowsFunction8<T1, T2, T3, T4, T5, T6, T7, T8, R> function(ThrowsFunction8<T1, T2, T3, T4, T5, T6, T7, T8, R> throwsFunction8) {
        return throwsFunction8::throwableApply;
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> SneakyThrowsFunction9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function(ThrowsFunction9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> throwsFunction9) {
        return throwsFunction9::throwableApply;
    }

    static SneakyThrowsRunnable runnable(ThrowsRunnable throwsRunnable) {
        return throwsRunnable::throwableRun;
    }

    static <T> SneakyThrowsSupplier<T> supplier(ThrowsSupplier<T> throwsSupplier) {
        return throwsSupplier::throwableGet;
    }
}
