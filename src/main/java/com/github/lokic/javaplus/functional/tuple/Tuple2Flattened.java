package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.tuple.*;

public interface Tuple2Flattened {

    static <T1, T2> Tuple2<T1, T2> flatten2(Tuple2<T1, T2> t) {
        return t;
    }

    static <T1, T2, T3> Tuple3<T1, T2, T3> flatten3(Tuple2<Tuple2<T1, T2>, T3> t) {
        return Tuple.of(t.getT1().getT1(), t.getT1().getT2(), t.getT2());
    }

    static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> flatten4(
            Tuple2<Tuple3<T1, T2, T3>, T4> t) {
        return Tuple.of(
                t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT2());
    }

    static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> flatten5(
            Tuple2<Tuple4<T1, T2, T3, T4>, T5> t) {
        return Tuple.of(
                t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT1().getT4(), t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> flatten6(
            Tuple2<Tuple5<T1, T2, T3, T4, T5>, T6> t) {
        return Tuple.of(
                t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT1().getT4(), t.getT1().getT5(), t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> flatten7(
            Tuple2<Tuple6<T1, T2, T3, T4, T5, T6>, T7> t) {
        return Tuple.of(
                t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT1().getT4(), t.getT1().getT5(), t.getT1().getT6(), t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> flatten8(
            Tuple2<Tuple7<T1, T2, T3, T4, T5, T6, T7>, T8> t) {
        return Tuple.of(
                t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT1().getT4(), t.getT1().getT5(), t.getT1().getT6(), t.getT1().getT7(), t.getT2());
    }

    static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> flatten9(
            Tuple2<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, T9> t) {
        return Tuple.of(
                t.getT1().getT1(), t.getT1().getT2(), t.getT1().getT3(), t.getT1().getT4(), t.getT1().getT5(), t.getT1().getT6(), t.getT1().getT7(), t.getT1().getT8(), t.getT2());
    }

}
