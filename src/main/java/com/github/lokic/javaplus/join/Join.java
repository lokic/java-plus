package com.github.lokic.javaplus.join;

import java.util.stream.Stream;

public class Join {

    public static <T> JoinStream<T> stream(Stream<T> stream) {
        return new JoinStream<>(stream);
    }

    public static <T1, T2> JoinType<T1, T2> innerJoin(Stream<T1> left, Stream<T2> right) {
        return new JoinType<>(left, right, t -> t.getT1() != null && t.getT2() != null);
    }

    public static <T1, T2> JoinType<T1, T2> leftOuterJoin(Stream<T1> left, Stream<T2> right) {
        return new JoinType<>(left, right, t -> t.getT1() != null);
    }

    public static <T1, T2> JoinType<T1, T2> rightOuterJoin(Stream<T1> left, Stream<T2> right) {
        return new JoinType<>(left, right, t -> t.getT2() != null);
    }

    public static <T1, T2> JoinType<T1, T2> fullOuterJoin(Stream<T1> left, Stream<T2> right) {
        return new JoinType<>(left, right, t -> !(t.getT1() == null && t.getT2() == null));
    }


}
