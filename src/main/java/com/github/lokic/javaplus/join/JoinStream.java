package com.github.lokic.javaplus.join;

import java.util.function.Function;
import java.util.stream.Stream;

public class JoinStream<T> {
    private final Stream<T> left;

    JoinStream(Stream<T> left) {
        this.left = left;
    }

    public Stream<T> stream() {
        return left;
    }

    public <R> Stream<R> flattenStream(Function<? super T, ? extends R> mapper) {
        return left.map(mapper);
    }

    public <T3> JoinType<T, T3> innerJoin(Stream<T3> right) {
        return Join.innerJoin(left, right);
    }

    public <T3> JoinType<T, T3> leftOuterJoin(Stream<T3> right) {
        return Join.leftOuterJoin(left, right);
    }

    public <T3> JoinType<T, T3> rightOuterJoin(Stream<T3> right) {
        return Join.rightOuterJoin(left, right);
    }

    public <T3> JoinType<T, T3> fullOuterJoin(Stream<T3> right) {
        return Join.fullOuterJoin(left, right);
    }

    public <U, K, R> JoinStream<R> innerJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        Stream<R> stream = innerJoin(right)
                .on(on.getLeftKey(), on.getRightKey())
                .flattenStream(on.getFlatten());
        return new JoinStream<>(stream);
    }

    public <U, K, R> JoinStream<R> leftOuterJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        Stream<R> stream = leftOuterJoin(right)
                .on(on.getLeftKey(), on.getRightKey())
                .flattenStream(on.getFlatten());
        return new JoinStream<>(stream);
    }


    public <U, K, R> JoinStream<R> rightOuterJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        Stream<R> stream = rightOuterJoin(right)
                .on(on.getLeftKey(), on.getRightKey())
                .flattenStream(on.getFlatten());
        return new JoinStream<>(stream);
    }


    public <U, K, R> JoinStream<R> fullOuterJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        Stream<R> stream = fullOuterJoin(right)
                .on(on.getLeftKey(), on.getRightKey())
                .flattenStream(on.getFlatten());
        return new JoinStream<>(stream);
    }
}
