package com.github.lokic.javaplus.join;

import com.github.lokic.javaplus.functional.function.Function2;
import com.github.lokic.javaplus.functional.function.Function3;
import com.github.lokic.javaplus.functional.function.Function4;
import com.github.lokic.javaplus.functional.function.Function5;
import com.github.lokic.javaplus.functional.tuple.Tuple2Flattened;
import com.github.lokic.javaplus.tuple.*;

import java.util.function.Function;

public class JoinOn<T, U, K, R> {

    private final Function<T, K> leftKey;
    private final Function<U, K> rightKey;
    private final Function<? super Tuple2<T, U>, ? extends R> flatten;

    private JoinOn(Function<T, K> leftKey, Function<U, K> rightKey, Function<? super Tuple2<T, U>, ? extends R> flatten) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.flatten = flatten;
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

    public Function<T, K> getLeftKey() {
        return leftKey;
    }

    public Function<U, K> getRightKey() {
        return rightKey;
    }

    public Function<? super Tuple2<T, U>, ? extends R> getFlatten() {
        return flatten;
    }
}
