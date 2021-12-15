package com.github.lokic.javaplus.functional.tuple;

import com.github.lokic.javaplus.tuple.*;

public class TupleFlattened {

    public static <T1, T2, T3> Tuple3<T1, T2, T3> flatten3(Tuple2<Tuple2<T1, T2>, T3> t) {
        return Tuple.of(
                t.getT1().getT1(),
                t.getT1().getT2(),
                t.getT2());
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> flatten4(
            Tuple2<Tuple2<Tuple2<T1, T2>, T3>, T4> t) {
        return Tuple.of(
                t.getT1().getT1().getT1(),
                t.getT1().getT1().getT2(),
                t.getT1().getT2(),
                t.getT2());
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
}
