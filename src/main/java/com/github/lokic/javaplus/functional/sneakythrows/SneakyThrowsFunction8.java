package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsFunction8;
import com.github.lokic.javaplus.functional.tuple.TupleFunction8;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsFunction8<T1, T2, T3, T4, T5, T6, T7, T8, R> extends TupleFunction8<T1, T2, T3, T4, T5, T6, T7, T8, R>, ThrowsFunction8<T1, T2, T3, T4, T5, T6, T7, T8, R> {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
        return throwableApply(t1, t2, t3, t4, t5, t6, t7, t8);
    }

}
