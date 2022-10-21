package com.github.lokic.javaplus.join;

import com.github.lokic.javaplus.Functions;
import com.github.lokic.javaplus.NullData;
import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoinType<T1, T2> {

    private final Stream<IndexWithData<Integer, Tuple2<T1, T2>>> leftWrappedStream;
    private final Stream<IndexWithData<Integer, Tuple2<T1, T2>>> rightWrappedStream;
    private final Predicate<Tuple2<T1, T2>> joinMatcher;

    JoinType(Stream<T1> left, Stream<T2> right, Predicate<Tuple2<T1, T2>> joinMatcher) {
        this.leftWrappedStream = left.map(l -> Tuple.of(Objects.requireNonNull(l), (T2) null)).map(Functions.mapWithIndex(IndexWithData::new));
        this.rightWrappedStream = right.map(r -> Tuple.of((T1) null, Objects.requireNonNull(r))).map(Functions.mapWithIndex(IndexWithData::new));
        this.joinMatcher = joinMatcher;
    }

    public <K> JoinStream<Tuple2<T1, T2>> on(Function<T1, K> leftKey, Function<T2, K> rightKey) {
        Stream<Tuple2<T1, T2>> stream = Stream.concat(leftWrappedStream, rightWrappedStream)
                .collect(Collectors.toMap(
                        t -> matchKey(t.getData(), leftKey, rightKey),
                        Collections::singletonList,
                        (a, b) -> {
                            List<IndexWithData<Integer, Tuple2<T1, T2>>> li = new ArrayList<>();
                            li.addAll(a);
                            li.addAll(b);
                            return li;
                        },
                        LinkedHashMap::new
                ))
                .values()
                .stream()
                .flatMap(this::cartesian)
                .sorted(this::compare)
                .filter(t -> joinMatcher.test(t.getData()))
                .map(IndexWithData::getData);
        return new JoinStream<>(stream);
    }

    /**
     * 笛卡尔积化
     *
     * @param li
     * @return
     */
    private Stream<IndexWithData<Tuple2<Integer, Integer>, Tuple2<T1, T2>>> cartesian(List<IndexWithData<Integer, Tuple2<T1, T2>>> li) {
        Map<Boolean, List<IndexWithData<Integer, Tuple2<T1, T2>>>> map = li.stream()
                .collect(Collectors.partitioningBy(t -> isLeft(t.getData()), this.toListOrNullList()));
        List<IndexWithData<Integer, Tuple2<T1, T2>>> left = map.get(true);
        List<IndexWithData<Integer, Tuple2<T1, T2>>> right = map.get(false);
        return left.stream()
                .flatMap(l -> right.stream()
                        .map(r -> merge(l, r)));
    }


    private IndexWithData<Tuple2<Integer, Integer>, Tuple2<T1, T2>> merge(IndexWithData<Integer, Tuple2<T1, T2>> l, IndexWithData<Integer, Tuple2<T1, T2>> r) {
        return new IndexWithData<>(
                Tuple.of(l == null ? null : l.getIndex(), r == null ? null : r.getIndex()),
                Tuple.of(l == null ? null : l.getData().getT1(), r == null ? null : r.getData().getT2()));
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

    private int compare(IndexWithData<Tuple2<Integer, Integer>, Tuple2<T1, T2>> a, IndexWithData<Tuple2<Integer, Integer>, Tuple2<T1, T2>> b) {
        Tuple2<Integer, Integer> aIndex = a.getIndex();
        Tuple2<Integer, Integer> bIndex = b.getIndex();

        int cmp = compare(aIndex.getT1(), bIndex.getT1());
        if (cmp == 0) {
            return compare(aIndex.getT2(), bIndex.getT2());
        }
        return cmp;
    }


    private int compare(Integer a, Integer b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return 1;
        }
        if (b == null) {
            return -1;
        }
        return Integer.compare(a, b);
    }

    @AllArgsConstructor
    @Getter
    private static class IndexWithData<K, T> {
        private K index;
        private T data;
    }

}
