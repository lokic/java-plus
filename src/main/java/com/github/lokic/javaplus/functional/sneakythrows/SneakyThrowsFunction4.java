package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsFunction4;
import com.github.lokic.javaplus.functional.tuple.TupleFunction4;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsFunction4<T1, T2, T3, T4, R> extends TupleFunction4<T1, T2, T3, T4, R>, ThrowsFunction4<T1, T2, T3, T4, R> {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
        return throwableApply(t1, t2, t3, t4);
    }

}
