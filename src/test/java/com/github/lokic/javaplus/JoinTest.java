package com.github.lokic.javaplus;

import com.github.lokic.javaplus.functional.tuple.TupleFlattened;
import com.github.lokic.javaplus.functional.tuple.TupleFunctional;
import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import com.github.lokic.javaplus.tuple.Tuple3;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class JoinTest {

    @Test
    public void test_innerJoin() {
        List<Tuple2<Integer, String>> list = Join.innerJoin(Stream.of(1, 2, 3, 4),
                Stream.of("2", "3", "5"))
            .on(String::valueOf, Function.identity())
            .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(2, "2"), Tuple.of(3, "3"));
    }

    @Test
    public void test_innerJoin_repeatKey() {
        List<Tuple2<Integer, String>> list = Join.innerJoin(Stream.of(1, 2, 2, 3, 4),
                Stream.of("2", "3", "5"))
            .on(String::valueOf, Function.identity())
            .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(2, "2"), Tuple.of(2, "2"), Tuple.of(3, "3"));
    }

    @Test
    public void test_leftOuterJoin() {
        List<Tuple2<Integer, String>> list = Join.leftOuterJoin(Stream.of(1, 2, 3, 4),
                Stream.of("2", "3", "5"))
            .on(String::valueOf, Function.identity())
            .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(1, null), Tuple.of(2, "2"), Tuple.of(3, "3"), Tuple.of(4, null));
    }

    @Test
    public void test_leftOuterJoin_empty() {
        List<Tuple2<Integer, String>> list = Join.leftOuterJoin(Stream.of(1, 2, 3, 4),
                Stream.<String>empty())
            .on(String::valueOf, Function.identity())
            .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(1, null), Tuple.of(2, null), Tuple.of(3, null), Tuple.of(4, null));
    }

    @Test
    public void test_rightOuterJoin() {
        List<Tuple2<Integer, String>> list = Join.rightOuterJoin(Stream.of(1, 2, 3, 4),
                Stream.of("2", "3", "5"))
            .on(String::valueOf, Function.identity())
            .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(2, "2"), Tuple.of(3, "3"), Tuple.of(null, "5"));
    }


    @Test
    public void test_fullOuterJoin() {
        List<Tuple2<Integer, String>> list = Join.fullOuterJoin(Stream.of(1, 2, 3, 4),
                Stream.of("2", "3", "5"))
            .on(String::valueOf, Function.identity())
            .stream()
            .collect(Collectors.toList());

        Assertions.assertThat(list)
            .containsExactly(Tuple.of(1, null), Tuple.of(2, "2"), Tuple.of(3, "3"),
                Tuple.of(4, null), Tuple.of(null, "5"));
    }

    @Test
    public void test_joinStream_leftOuterJoin() {
        List<Tuple3<Integer, String, String>> list =
            Join.leftOuterJoin(Stream.of(1, 2, 3, 4), Stream.of("2", "3", "5"))
                .on(String::valueOf, Function.identity())
                .leftOuterJoin(Stream.of("1"))
                .on(TupleFunctional.function((a, b) -> String.valueOf(a)), Function.identity())
                .flattenStream(TupleFlattened::flatten3)
                .collect(Collectors.toList());

        Assertions.assertThat(list)
            .containsExactly(
                Tuple.of(1, null, "1"),
                Tuple.of(2, "2", null),
                Tuple.of(3, "3", null),
                Tuple.of(4, null, null));
    }
}