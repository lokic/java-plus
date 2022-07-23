package com.github.lokic.javaplus.join;

import com.github.lokic.javaplus.functional.function.*;
import com.github.lokic.javaplus.functional.tuple.Tuple2Flattened;
import com.github.lokic.javaplus.tuple.*;

import java.util.function.Function;
import java.util.stream.Stream;

public class Join {

    public static <T> JoinStream<T> stream(Stream<T> stream) {
        return new JoinStream<>(stream);
    }

    public static <T1, TT, KK> JoinOn<T1, TT, KK, Tuple2<T1, TT>> on(Function<T1, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(f1, f2, Tuple2Flattened::flatten2);
    }

    public static <T1, T2, TT, KK> JoinOn<Tuple2<T1, T2>, TT, KK, Tuple3<T1, T2, TT>> on(Function2<T1, T2, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(t -> f1.apply(t.getT1(), t.getT2()), f2, Tuple2Flattened::flatten3);
    }

    public static <T1, T2, T3, TT, KK> JoinOn<Tuple3<T1, T2, T3>, TT, KK, Tuple4<T1, T2, T3, TT>> on(Function3<T1, T2, T3, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(t -> f1.apply(t.getT1(), t.getT2(), t.getT3()), f2, Tuple2Flattened::flatten4);
    }

    public static <T1, T2, T3, T4, TT, KK> JoinOn<Tuple4<T1, T2, T3, T4>, TT, KK, Tuple5<T1, T2, T3, T4, TT>> on(Function4<T1, T2, T3, T4, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(t -> f1.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4()), f2, Tuple2Flattened::flatten5);
    }

    public static <T1, T2, T3, T4, T5, TT, KK> JoinOn<Tuple5<T1, T2, T3, T4, T5>, TT, KK, Tuple6<T1, T2, T3, T4, T5, TT>> on(Function5<T1, T2, T3, T4, T5, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(t -> f1.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5()), f2, Tuple2Flattened::flatten6);
    }

    public static <T1, T2, T3, T4, T5, T6, TT, KK> JoinOn<Tuple6<T1, T2, T3, T4, T5, T6>, TT, KK, Tuple7<T1, T2, T3, T4, T5, T6, TT>> on(Function6<T1, T2, T3, T4, T5, T6, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(t -> f1.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6()), f2, Tuple2Flattened::flatten7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, TT, KK> JoinOn<Tuple7<T1, T2, T3, T4, T5, T6, T7>, TT, KK, Tuple8<T1, T2, T3, T4, T5, T6, T7, TT>> on(Function7<T1, T2, T3, T4, T5, T6, T7, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(t -> f1.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7()), f2, Tuple2Flattened::flatten8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, TT, KK> JoinOn<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, TT, KK, Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, TT>> on(Function8<T1, T2, T3, T4, T5, T6, T7, T8, KK> f1, Function<TT, KK> f2) {
        return new JoinOn<>(t -> f1.apply(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7(), t.getT8()), f2, Tuple2Flattened::flatten9);
    }

}
