package com.github.lokic.javaplus;

import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;

import java.util.*;
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

    public static <T1, T2> JoinType<T1, T2> innerJoin(Collection<T1> left, Collection<T2> right) {
        return innerJoin(left.stream(), right.stream());
    }

    public static <T1, T2> JoinType<T1, T2> leftOuterJoin(Collection<T1> left, Collection<T2> right) {
        return leftOuterJoin(left.stream(), right.stream());
    }

    public static <T1, T2> JoinType<T1, T2> rightOuterJoin(Collection<T1> left, Collection<T2> right) {
        return rightOuterJoin(left.stream(), right.stream());
    }

    public static <T1, T2> JoinType<T1, T2> fullOuterJoin(Collection<T1> left, Collection<T2> right) {
        return fullOuterJoin(left.stream(), right.stream());
    }

    public static class JoinStream<T1, T2> {

        private final Stream<Tuple2<T1, T2>> left;

        private JoinStream(Stream<Tuple2<T1, T2>> stream) {
            this.left = stream;
        }

        public Stream<Tuple2<T1, T2>> stream() {
            return left;
        }

        public <R> Stream<R> flattenStream(Function<? super Tuple2<T1, T2>, ? extends R> mapper) {
            return left.map(mapper);
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> innerJoin(Stream<T3> right) {
            return Join.innerJoin(left, right);
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> leftOuterJoin(Stream<T3> right) {
            return Join.leftOuterJoin(left, right);
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> rightOuterJoin(Stream<T3> right) {
            return Join.rightOuterJoin(left, right);
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> fullOuterJoin(Stream<T3> right) {
            return Join.fullOuterJoin(left, right);
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> innerJoin(Collection<T3> right) {
            return innerJoin(right.stream());
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> leftOuterJoin(Collection<T3> right) {
            return leftOuterJoin(right.stream());
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> rightOuterJoin(Collection<T3> right) {
            return rightOuterJoin(right.stream());
        }

        public <T3> JoinType<Tuple2<T1, T2>, T3> fullOuterJoin(Collection<T3> right) {
            return fullOuterJoin(right.stream());
        }
    }

    public static class JoinType<T1, T2> {

        private final Stream<Tuple2<T1, T2>> leftWrappedStream;
        private final Stream<Tuple2<T1, T2>> rightWrappedStream;
        private final Predicate<Tuple2<T1, T2>> joinMatcher;

        private JoinType(Stream<T1> left, Stream<T2> right, Predicate<Tuple2<T1, T2>> joinMatcher) {
            this.leftWrappedStream = left.map(l -> Tuple.of(Objects.requireNonNull(l), null));
            this.rightWrappedStream = right.map(r -> Tuple.of(null, Objects.requireNonNull(r)));
            this.joinMatcher = joinMatcher;
        }

        public <K> JoinStream<T1, T2> on(Function<T1, K> leftKey, Function<T2, K> rightKey) {
            Stream<Tuple2<T1, T2>> stream = Stream.concat(leftWrappedStream, rightWrappedStream)
                    .collect(Collectors.toMap(
                            t -> matchKey(t, leftKey, rightKey),
                            Collections::singletonList,
                            (a, b) -> {
                                List<Tuple2<T1, T2>> li = new ArrayList<>();
                                li.addAll(a);
                                li.addAll(b);
                                return li;
                            },
                            LinkedHashMap::new
                    ))
                    .values()
                    .stream()
                    .flatMap(this::cartesian)
                    .filter(this.joinMatcher);
            return new JoinStream<>(stream);
        }

        /**
         * 笛卡尔积化
         *
         * @param li
         * @return
         */
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
                            return NullData.nullList();
                        }
                        return l;
                    }
            );
        }
    }

}
