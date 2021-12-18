package com.github.lokic.javaplus.functional.sneakythrows;

import com.github.lokic.javaplus.functional.throwable.ThrowsFunction2;
import com.github.lokic.javaplus.functional.tuple.TupleFunction2;
import lombok.SneakyThrows;

@FunctionalInterface
public interface SneakyThrowsFunction2<T1, T2, R> extends TupleFunction2<T1, T2, R>, ThrowsFunction2<T1, T2, R> {

    @SneakyThrows
    @Override
    default R apply(T1 t1, T2 t2) {
        return throwableApply(t1, t2);
    }

}
