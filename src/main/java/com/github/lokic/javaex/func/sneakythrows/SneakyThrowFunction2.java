package com.github.lokic.javaex.func.sneakythrows;

import com.github.lokic.javaex.func.tuple.TupleFunction2;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowFunction2<T1, T2, R> extends TupleFunction2<T1, T2, R> {

    R throwableApply(T1 t1, T2 t2) throws Exception;

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2) {
        return throwableApply(t1, t2);
    }
}
