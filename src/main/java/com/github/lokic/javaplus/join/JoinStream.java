package com.github.lokic.javaplus.join;

import com.github.lokic.javaplus.tuple.Tuple;

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
        Stream<R> stream = new JoinStream<>(right)
                .leftOuterJoin(left)
                .on(on.getRightKey(), on.getLeftKey())
                .flattenStream(t -> on.getFlatten().apply(Tuple.of(t.getT2(), t.getT1())));
        return new JoinStream<>(stream);
    }


    public <U, K, R> JoinStream<R> fullOuterJoin(Stream<U> right, JoinOn<T, U, K, R> on) {
        Stream<R> stream = fullOuterJoin(right)
                .on(on.getLeftKey(), on.getRightKey())
                .flattenStream(on.getFlatten());
        return new JoinStream<>(stream);
    }

    private <U> JoinType<T, U> innerJoin(Stream<U> right) {
        return new JoinType<>(left, right, t -> t.getT1() != null && t.getT2() != null);
    }

    private <U> JoinType<T, U> leftOuterJoin(Stream<U> right) {
        return new JoinType<>(left, right, t -> t.getT1() != null);
    }

    private <U> JoinType<T, U> fullOuterJoin(Stream<U> right) {
        return new JoinType<>(left, right, t -> !(t.getT1() == null && t.getT2() == null));
    }

}
