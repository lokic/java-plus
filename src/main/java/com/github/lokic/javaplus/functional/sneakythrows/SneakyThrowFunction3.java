package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowFunction3;
import com.github.lokic.javaplus.functional.tuple.TupleFunction3;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowFunction3<T1, T2, T3, R> extends TupleFunction3<T1, T2, T3, R>, ThrowFunction3<T1, T2, T3, R> {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2, T3 t3) {
        return throwableApply(t1, t2, t3);
    }

}
