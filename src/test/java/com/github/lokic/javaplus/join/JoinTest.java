package com.github.lokic.javaplus.join;

import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import com.github.lokic.javaplus.tuple.Tuple3;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.lokic.javaplus.join.JoinOn.on;

public class JoinTest {

    @Test
    public void test_innerJoin() {

        List<Tuple2<Integer, String>> list = Join.stream(Stream.of(1, 2, 3, 4))
                .innerJoin(Stream.of("2", "3", "5"), on(i -> String.valueOf(i), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(2, "2"), Tuple.of(3, "3"));
    }

    @Test
    public void test_innerJoin_repeatKey() {

        List<Tuple2<Integer, String>> list = Join.stream(Stream.of(1, 2, 2, 3, 4))
                .innerJoin(Stream.of("2", "3", "5"), on(i -> String.valueOf(i), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(2, "2"), Tuple.of(2, "2"), Tuple.of(3, "3"));
    }

    @Test
    public void test_leftOuterJoin() {
        List<Tuple2<Integer, String>> list = Join.stream(Stream.of(1, 2, 3, 4))
                .leftOuterJoin(Stream.of("2", "3", "5"), on(i -> String.valueOf(i), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(1, null), Tuple.of(2, "2"), Tuple.of(3, "3"), Tuple.of(4, null));
    }

    @Test
    public void test_leftOuterJoin_empty() {
        List<Tuple2<Integer, String>> list = Join.stream(Stream.of(1, 2, 3, 4))
                .leftOuterJoin(Stream.empty(), on(i -> String.valueOf(i), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(1, null), Tuple.of(2, null), Tuple.of(3, null), Tuple.of(4, null));
    }

    @Test
    public void test_rightOuterJoin() {
        List<Tuple2<Integer, String>> list = Join.stream(Stream.of(1, 2, 3, 4))
                .rightOuterJoin(Stream.of("2", "3", "5"), on(i -> String.valueOf(i), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(2, "2"), Tuple.of(3, "3"), Tuple.of(null, "5"));
    }


    @Test
    public void test_fullOuterJoin() {
        List<Tuple2<Integer, String>> list = Join.stream(Stream.of(1, 2, 3, 4))
                .fullOuterJoin(Stream.of("2", "3", "5"), on(i -> String.valueOf(i), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(Tuple.of(1, null), Tuple.of(2, "2"), Tuple.of(3, "3"),
                        Tuple.of(4, null), Tuple.of(null, "5"));
    }

    @Test
    public void test_joinStream_leftOuterJoin() {
        List<Tuple3<Integer, String, String>> list = Join.stream(Stream.of(1, 2, 3, 4))
                .leftOuterJoin(Stream.of("2", "3", "5"), on(i -> String.valueOf(i), Function.identity()))
                .leftOuterJoin(Stream.of("1"), on((a, b) -> String.valueOf(a), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(list)
                .containsExactly(
                        Tuple.of(1, null, "1"),
                        Tuple.of(2, "2", null),
                        Tuple.of(3, "3", null),
                        Tuple.of(4, null, null));
    }

    @Test
    public void test_nullKey() {
        List<Tuple2<Integer, Integer>> list = Join.stream(Stream.of(new KeyBox(1), new KeyBox(2), new KeyBox(3), new KeyBox(4), new KeyBox(null)))
                .leftOuterJoin(Stream.of(2, 3, 5), on(KeyBox::getKey, Function.identity()))
                .stream()
                .map(t -> Tuple.of(t.getT1().getKey(), t.getT2()))
                .collect(Collectors.toList());
        Assertions.assertThat(list)
                .containsExactly(Tuple.of(1, null), Tuple.of(2, 2), Tuple.of(3, 3),
                        Tuple.of(4, null), Tuple.of(null, null));

    }

    public static class KeyBox {
        private final Integer key;

        public KeyBox(Integer key) {
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }
    }
}