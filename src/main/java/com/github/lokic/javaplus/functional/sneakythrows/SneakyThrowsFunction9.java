package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsFunction9;
import com.github.lokic.javaplus.functional.tuple.TupleFunction9;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsFunction9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> extends TupleFunction9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R>, ThrowsFunction9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
        return throwableApply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
    }

}
