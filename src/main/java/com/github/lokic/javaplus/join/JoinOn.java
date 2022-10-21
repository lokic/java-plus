package com.github.lokic.javaplus.join;

import com.github.lokic.javaplus.tuple.Tuple2;

import java.util.function.Function;

public class JoinOn<T, U, K, R> {

    private final Function<T, K> leftKey;
    private final Function<U, K> rightKey;
    private final Function<? super Tuple2<T, U>, ? extends R> flatten;

    JoinOn(Function<T, K> leftKey, Function<U, K> rightKey, Function<? super Tuple2<T, U>, ? extends R> flatten) {
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.flatten = flatten;
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
