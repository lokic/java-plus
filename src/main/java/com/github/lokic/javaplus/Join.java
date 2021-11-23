package com.github.lokic.javaplus;

import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Join {


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


    public static class JoinType<T1, T2> {
        private final Stream<Tuple2<T1, T2>> leftWrappedStream;
        private final Stream<Tuple2<T1, T2>> rightWrappedStream;
        private final Predicate<Tuple2<T1, T2>> joinMatcher;

        public JoinType(Stream<T1> left, Stream<T2> right, Predicate<Tuple2<T1, T2>> joinMatcher) {
            this.leftWrappedStream = left.map(l -> Tuple.of(Objects.requireNonNull(l), null));
            this.rightWrappedStream = right.map(r -> Tuple.of(null, Objects.requireNonNull(r)));
            this.joinMatcher = joinMatcher;
        }

        public <K> Stream<Tuple2<T1, T2>> on(Function<T1, K> leftKey, Function<T2, K> rightKey) {
            return Stream.concat(leftWrappedStream, rightWrappedStream)
                    .collect(Collectors.groupingBy(t -> matchKey(t, leftKey, rightKey)))
                    .values()
                    .stream()
                    .flatMap(this::cartesian)
                    .filter(this.joinMatcher);
        }

        private Stream<Tuple2<T1, T2>> cartesian(List<Tuple2<T1, T2>> li) {
            Map<Boolean, List<Tuple2<T1, T2>>> map = li.stream()
                    .collect(Collectors.partitioningBy(this::isLeft, this.toListOrNullList()));
            List<Tuple2<T1, T2>> left = map.get(true);
            List<Tuple2<T1, T2>> right = map.get(false);
            return left.stream()
                    .flatMap(l -> right.stream()
                            .map(r -> this.merge(l, r)));
        }

        private Tuple2<T1, T2> merge(Tuple2<T1, T2> l, Tuple2<T1, T2> r) {
            return Tuple.of(l == null ? null : l.getT1(), r == null ? null : r.getT2());
        }

        private boolean isLeft(Tuple2<T1, T2> t) {
            return t.getT1() != null;
        }

        private <K> K matchKey(Tuple2<T1, T2> t, Function<T1, K> leftKey, Function<T2, K> rightKey) {
            if (t.getT1() != null) {
                return leftKey.apply(t.getT1());
            }
            if (t.getT2() != null) {
                return rightKey.apply(t.getT2());
            }
            throw new IllegalStateException("t1 == null and t2 == null");
        }

        private <T> Collector<T, List<T>, List<T>> toListOrNullList() {
            return Collector.of(
                    ArrayList::new,
                    List::add,
                    (left, right) -> {
                        left.addAll(right);
                        return left;
                    },
                    l -> {
                        if (l == null || l.isEmpty()) {
                            return nullList();
                        }
                        return l;
                    }
            );
        }

        private <T> List<T> nullList() {
            List<T> li = new ArrayList<>();
            li.add(null);
            return li;
        }
    }

}
