package com.github.lokic.javaplus;

import com.github.lokic.javaplus.tuple.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;

import static com.github.lokic.javaplus.OtherCollectors.filtering;

public class TupleCollectors {

    public static <T1, T2>
    Collector<Tuple2<T1, T2>, ?, Map<T1, List<T2>>> groupingBy1() {
        return java.util.stream.Collectors.groupingBy(
                Tuple2::getT1,
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple2::getT2, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }

    public static <T1, T2, T3>
    Collector<Tuple3<T1, T2, T3>, ?, Map<Tuple2<T1, T2>, List<T3>>> groupingBy2() {
        return java.util.stream.Collectors.groupingBy(
                t -> Tuple.of(t.getT1(), t.getT2()),
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple3::getT3, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }

    public static <T1, T2, T3, T4>
    Collector<Tuple4<T1, T2, T3, T4>, ?, Map<Tuple3<T1, T2, T3>, List<T4>>> groupingBy3() {
        return java.util.stream.Collectors.groupingBy(
                t -> Tuple.of(t.getT1(), t.getT2(), t.getT3()),
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple4::getT4, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }

    public static <T1, T2, T3, T4, T5>
    Collector<Tuple5<T1, T2, T3, T4, T5>, ?, Map<Tuple4<T1, T2, T3, T4>, List<T5>>> groupingBy4() {
        return java.util.stream.Collectors.groupingBy(
                t -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4()),
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple5::getT5, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }

    public static <T1, T2, T3, T4, T5, T6>
    Collector<Tuple6<T1, T2, T3, T4, T5, T6>, ?, Map<Tuple5<T1, T2, T3, T4, T5>, List<T6>>> groupingBy5() {
        return java.util.stream.Collectors.groupingBy(
                t -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5()),
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple6::getT6, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }

    public static <T1, T2, T3, T4, T5, T6, T7>
    Collector<Tuple7<T1, T2, T3, T4, T5, T6, T7>, ?, Map<Tuple6<T1, T2, T3, T4, T5, T6>, List<T7>>> groupingBy6() {
        return java.util.stream.Collectors.groupingBy(
                t -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6()),
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple7::getT7, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8>
    Collector<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, ?, Map<Tuple7<T1, T2, T3, T4, T5, T6, T7>, List<T8>>> groupingBy7() {
        return java.util.stream.Collectors.groupingBy(
                t -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7()),
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple8::getT8, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9>
    Collector<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, ?, Map<Tuple8<T1, T2, T3, T4, T5, T6, T7, T8>, List<T9>>> groupingBy8() {
        return java.util.stream.Collectors.groupingBy(
                t -> Tuple.of(t.getT1(), t.getT2(), t.getT3(), t.getT4(), t.getT5(), t.getT6(), t.getT7(), t.getT8()),
                LinkedHashMap::new,
                java.util.stream.Collectors.mapping(Tuple9::getT9, filtering(Objects::nonNull, java.util.stream.Collectors.toList())));
    }


}
