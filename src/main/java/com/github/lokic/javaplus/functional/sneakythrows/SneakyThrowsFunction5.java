package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsFunction5;
import com.github.lokic.javaplus.functional.tuple.TupleFunction5;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsFunction5<T1, T2, T3, T4, T5, R> extends TupleFunction5<T1, T2, T3, T4, T5, R>, ThrowsFunction5<T1, T2, T3, T4, T5, R> {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        return throwableApply(t1, t2, t3, t4, t5);
    }

}
