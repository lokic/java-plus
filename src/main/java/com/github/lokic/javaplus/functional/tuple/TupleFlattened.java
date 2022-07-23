package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.functional.function.*;
import com.github.lokic.javaplus.tuple.*;

import java.util.function.Function;

public interface TupleFlattened {

    static <T1, T2, T3> Tuple3<T1, T2, T3> flatten3(Tuple2<Tuple2<T1, T2>, T3> t) {
        return Tuple.of(t.getT1().getT1(), t.getT1().getT2(), t.getT2());
    }

    static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> flatten4(
            Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4> t) {
        return Tuple.of(
                t.getT1().getT1().getT1(), t.getT1().getT1().getT2(), t.getT1().getT2(), t.getT2());
    }

    static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> flatten5(
            Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5> t) {
        return Tuple.of(
                t.getT1().getT1().getT1().getT1(),
                t.getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> flatten6(
            Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6> t) {
        return Tuple.of(
                t.getT1().getT1().getT1().getT1().getT1(),
                t.getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> flatten7(
            Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, T7> t) {
        return Tuple.of(
                t.getT1().getT1().getT1().getT1().getT1().getT1(),
                t.getT1().getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> flatten8(
            Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, T7>, T8> t) {
        return Tuple.of(
                t.getT1().getT1().getT1().getT1().getT1().getT1().getT1(),
                t.getT1().getT1().getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> flatten9(
            Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, T7>, T8>, T9> t) {
        return Tuple.of(
                t.getT1().getT1().getT1().getT1().getT1().getT1().getT1().getT1(),
                t.getT1().getT1().getT1().getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT1().getT2(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
    }

    static <T1, T2, R> Function<Tuple2<T1, T2>, R> flatten(Function2<T1, T2, R> function) {
        return t -> function.apply(t.getT1(), t.getT2());
    }

    static <T1, T2, T3, R> Function<Tuple2<Tuple2<T1, T2>, T3>, R> flatten(
            Function3<T1, T2, T3, R> function) {
        return t -> {
            Tuple3<T1, T2, T3> t3 = flatten3(t);
            return function.apply(t3.getT1(), t3.getT2(), t3.getT3());
        };
    }

    static <T1, T2, T3, T4, R> Function<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, R> flatten(
            Function4<T1, T2, T3, T4, R> function) {
        return t -> {
            Tuple4<T1, T2, T3, T4> t4 = flatten4(t);
            return function.apply(t4.getT1(), t4.getT2(), t4.getT3(), t4.getT4());
        };
    }

    static <T1, T2, T3, T4, T5, R>
    Function<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, R> flatten(
            Function5<T1, T2, T3, T4, T5, R> function) {
        return t -> {
            Tuple5<T1, T2, T3, T4, T5> t5 = flatten5(t);
            return function.apply(t5.getT1(), t5.getT2(), t5.getT3(), t5.getT4(), t5.getT5());
        };
    }

    static <T1, T2, T3, T4, T5, T6, R>
    Function<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, R> flatten(
            Function6<T1, T2, T3, T4, T5, T6, R> function) {
        return t -> {
            Tuple6<T1, T2, T3, T4, T5, T6> t6 = flatten6(t);
            return function.apply(t6.getT1(), t6.getT2(), t6.getT3(), t6.getT4(), t6.getT5(), t6.getT6());
        };
    }

    static <T1, T2, T3, T4, T5, T6, T7, R>
    Function<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, T7>, R> flatten(
            Function7<T1, T2, T3, T4, T5, T6, T7, R> function) {
        return t -> {
            Tuple7<T1, T2, T3, T4, T5, T6, T7> t7 = flatten7(t);
            return function.apply(t7.getT1(), t7.getT2(), t7.getT3(), t7.getT4(), t7.getT5(), t7.getT6(), t7.getT7());
        };
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, R>
    Function<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, T7>, T8>, R> flatten(
            Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> function) {
        return t -> {
            Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> t8 = flatten8(t);
            return function.apply(t8.getT1(), t8.getT2(), t8.getT3(), t8.getT4(), t8.getT5(), t8.getT6(), t8.getT7(), t8.getT8());
        };
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R>
    Function<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4>, T5>, T6>, T7>, T8>, T9>, R> flatten(
            Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> function) {
        return t -> {
            Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> t9 = flatten9(t);
            return function.apply(t9.getT1(), t9.getT2(), t9.getT3(), t9.getT4(), t9.getT5(), t9.getT6(), t9.getT7(), t9.getT8(), t9.getT9());
        };
    }
}
