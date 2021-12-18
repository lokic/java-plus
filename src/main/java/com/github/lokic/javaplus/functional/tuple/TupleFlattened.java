package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.*;
import com.github.lokic.javaplus.tuple.*;

import java.util.function.Function;

public class TupleFlattened {

    public static <T1, T2, T3> Tuple3<T1, T2, T3> flatten3(Tuple2<Tuple2<T1, T2>, T3> t) {
        return Tuple.of(t.getT1().getT1(), t.getT1().getT2(), t.getT2());
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> flatten4(
            Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4> t) {
        return Tuple.of(
                t.getT1().getT1().getT1(), t.getT1().getT1().getT2(), t.getT1().getT2(), t.getT2());
    }

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> flatten5(
            Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5> t) {
        return Tuple.of(
                t.getT1().getT1().getT1().getT1(),
                t.getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
    }

    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> flatten6(
            Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6> t) {
        return Tuple.of(
                t.getT1().getT1().getT1().getT1().getT1(),
                t.getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
    }

    public static <T1, T2, R> Function<Tuple2<T1, T2>, R> flatten(Function2<T1, T2, R> function) {
        return t -> function.apply(t.getT1(), t.getT2());
    }

    public static <T1, T2, T3, R> Function<Tuple2<Tuple2<T1, T2>, T3>, R> flatten(
            Function3<T1, T2, T3, R> function) {
        return t -> {
            Tuple3<T1, T2, T3> t3 = flatten3(t);
            return function.apply(t3.getT1(), t3.getT2(), t3.getT3());
        };
    }

    public static <T1, T2, T3, T4, R> Function<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, R> flatten(
            Function4<T1, T2, T3, T4, R> function) {
        return t -> {
            Tuple4<T1, T2, T3, T4> t4 = flatten4(t);
            return function.apply(t4.getT1(), t4.getT2(), t4.getT3(), t4.getT4());
        };
    }

    public static <T1, T2, T3, T4, T5, R>
    Function<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, R> flatten(
            Function5<T1, T2, T3, T4, T5, R> function) {
        return t -> {
            Tuple5<T1, T2, T3, T4, T5> t5 = flatten5(t);
            return function.apply(t5.getT1(), t5.getT2(), t5.getT3(), t5.getT4(), t5.getT5());
        };
    }

    public static <T1, T2, T3, T4, T5, T6, R>
    Function<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, R> flatten(
            Function6<T1, T2, T3, T4, T5, T6, R> function) {
        return t -> {
            Tuple6<T1, T2, T3, T4, T5, T6> t6 = flatten6(t);
            return function.apply(t6.getT1(), t6.getT2(), t6.getT3(), t6.getT4(), t6.getT5(), t6.getT6());
        };
    }
}
