package com.github.lokic.javaplus.func.sneakythrows;

import com.github.lokic.javaplus.func.throwable.ThrowFunction2;
import com.github.lokic.javaplus.func.tuple.TupleFunction2;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowFunction2<T1, T2, R> extends TupleFunction2<T1, T2, R>, ThrowFunction2<T1, T2, R>, SneakyThrow {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2) {
        return throwableApply(t1, t2);
    }

}