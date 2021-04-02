package com.github.lokic.javaex.func.sneakythrows;

import com.github.lokic.javaex.func.tuple.TupleFunction3;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowFunction3<T1, T2, T3, R> extends TupleFunction3<T1, T2, T3, R> {

    R throwableApply(T1 t1, T2 t2, T3 t3) throws Exception;

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2, T3 t3) {
        return throwableApply(t1, t2, t3);
    }
}
