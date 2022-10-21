package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsFunction6;
import com.github.lokic.javaplus.functional.tuple.TupleFunction6;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsFunction6<T1, T2, T3, T4, T5, T6, R> extends TupleFunction6<T1, T2, T3, T4, T5, T6, R>, ThrowsFunction6<T1, T2, T3, T4, T5, T6, R> {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        return throwableApply(t1, t2, t3, t4, t5, t6);
    }

}
