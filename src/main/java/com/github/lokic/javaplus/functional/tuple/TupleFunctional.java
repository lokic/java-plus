package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.consumer.Consumer2;
import com.github.lokic.javaplus.functional.consumer.Consumer3;
import com.github.lokic.javaplus.functional.function.*;

public interface TupleFunctional {
    static <T1, T2> TupleConsumer2<T1, T2> cast(Consumer2<T1, T2> consumer2) {
        return consumer2::accept;
    }

    static <T1, T2, T3> TupleConsumer3<T1, T2, T3> cast(Consumer3<T1, T2, T3> consumer3) {
        return consumer3::accept;
    }

    static <T1, T2, R> TupleFunction2<T1, T2, R> cast(Function2<T1, T2, R> function2) {
        return function2::apply;
    }

    static <T1, T2, T3, R> TupleFunction3<T1, T2, T3, R> cast(Function3<T1, T2, T3, R> function3) {
        return function3::apply;
    }

    static <T1, T2, T3, T4, R> TupleFunction4<T1, T2, T3, T4, R> cast(Function4<T1, T2, T3, T4, R> function4) {
        return function4::apply;
    }

    static <T1, T2, T3, T4, T5, R> TupleFunction5<T1, T2, T3, T4, T5, R> cast(Function5<T1, T2, T3, T4, T5, R> function5) {
        return function5::apply;
    }

    static <T1, T2, T3, T4, T5, T6, R> TupleFunction6<T1, T2, T3, T4, T5, T6, R> cast(Function6<T1, T2, T3, T4, T5, T6, R> function6) {
        return function6::apply;
    }
}
