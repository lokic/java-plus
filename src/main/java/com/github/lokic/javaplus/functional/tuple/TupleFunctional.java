package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.consumer.Consumer2;
import com.github.lokic.javaplus.functional.consumer.Consumer3;
import com.github.lokic.javaplus.functional.function.*;
import com.github.lokic.javaplus.functional.predicate.Predicate2;
import com.github.lokic.javaplus.functional.predicate.Predicate3;

public interface TupleFunctional {

    static <T1, T2> TupleConsumer2<T1, T2> consumer(Consumer2<T1, T2> consumer2) {
        return consumer2::accept;
    }

    static <T1, T2, T3> TupleConsumer3<T1, T2, T3> consumer(Consumer3<T1, T2, T3> consumer3) {
        return consumer3::accept;
    }


    static <T1, T2> TuplePredicate2<T1, T2> predicate(Predicate2<T1, T2> predicate2) {
        return predicate2::test;
    }

    static <T1, T2, T3> TuplePredicate3<T1, T2, T3> predicate(Predicate3<T1, T2, T3> predicate3) {
        return predicate3::test;
    }


    static <T1, T2, R> TupleFunction2<T1, T2, R> function(Function2<T1, T2, R> function2) {
        return function2::apply;
    }

    static <T1, T2, T3, R> TupleFunction3<T1, T2, T3, R> function(Function3<T1, T2, T3, R> function3) {
        return function3::apply;
    }

    static <T1, T2, T3, T4, R> TupleFunction4<T1, T2, T3, T4, R> function(Function4<T1, T2, T3, T4, R> function4) {
        return function4::apply;
    }

    static <T1, T2, T3, T4, T5, R> TupleFunction5<T1, T2, T3, T4, T5, R> function(Function5<T1, T2, T3, T4, T5, R> function5) {
        return function5::apply;
    }

    static <T1, T2, T3, T4, T5, T6, R> TupleFunction6<T1, T2, T3, T4, T5, T6, R> function(Function6<T1, T2, T3, T4, T5, T6, R> function6) {
        return function6::apply;
    }
}
