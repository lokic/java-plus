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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JoinType<T1, T2> {

    private static final Comparator<?> CMP =
            Comparator.comparing((IndexWithData<Tuple2<Integer, Integer>, ?> indexData) -> indexData.getIndex(),
                    Comparator.comparing((Tuple2<Integer, Integer> t) -> t.getT1(), Comparator.nullsLast(Integer::compare))
                            .thenComparing(Tuple2::getT2, Comparator.nullsLast(Integer::compare)));

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
                .collect(Collectors.groupingBy(
                        t -> matchKey(t.getData(), leftKey, rightKey),
                        LinkedHashMap::new,
                        Collectors.toList()
                ))
                .values()
                .stream()
                .flatMap(this::cartesian)
                .sorted(comparator())
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
                .collect(Collectors.groupingBy(t -> isLeft(t.getData())));
        List<IndexWithData<Integer, Tuple2<T1, T2>>> left = map.getOrDefault(true, NullData.nullList());
        List<IndexWithData<Integer, Tuple2<T1, T2>>> right = map.getOrDefault(false, NullData.nullList());
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

    private <K> Optional<K> matchKey(Tuple2<T1, T2> t, Function<T1, K> leftKey, Function<T2, K> rightKey) {
        if (t.getT1() != null) {
            return Optional.ofNullable(leftKey.apply(t.getT1()));
        }
        if (t.getT2() != null) {
            return Optional.ofNullable(rightKey.apply(t.getT2()));
        }
        throw new IllegalStateException("t1 == null and t2 == null");
    }

    private Comparator<IndexWithData<Tuple2<Integer, Integer>, Tuple2<T1, T2>>> comparator() {
        @SuppressWarnings("unchecked")
        Comparator<IndexWithData<Tuple2<Integer, Integer>, Tuple2<T1, T2>>> cmp
                = (Comparator<IndexWithData<Tuple2<Integer, Integer>, Tuple2<T1, T2>>>) CMP;
        return cmp;
    }

    @AllArgsConstructor
    @Getter
    private static class IndexWithData<K, T> {
        private final K index;
        private final T data;
    }

}
