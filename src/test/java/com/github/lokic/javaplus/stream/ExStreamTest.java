package com.github.lokic.javaplus.stream;

import com.github.lokic.javaplus.tuple.Tuple;
import com.github.lokic.javaplus.tuple.Tuple2;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.lokic.javaplus.join.Join.on;
import static com.github.lokic.javaplus.stream.ExStreamTest.Func.*;

public class ExStreamTest {


    @Test
    public void of_varargs() {
        List<String> li = ExStream.of("1", "2", "3")
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "2", "3"), li);
    }

    @Test
    public void of_collection() {
        List<String> li = ExStream.of(Lists.newArrayList("1", "2", "3"))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "2", "3"), li);
    }

    @Test
    public void of_stream() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "2", "3"), li);
    }

    @Test
    public void func() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .func(s -> s.filter(x -> !"2".equals(x)))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "3"), li);
    }

    @Test
    public void func_filter() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .func(filter(x -> !"2".equals(x)))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "3"), li);
    }

    @Test
    public void test_filter() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .filter(x -> !"2".equals(x))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "3"), li);
    }

    @Test
    public void test_map() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .func(map(x -> "A" + x))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("A1", "A2", "A3"), li);
    }

    @Test
    public void func_map() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .map(x -> "A" + x)
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("A1", "A2", "A3"), li);
    }

    @Test
    public void func_flatMap() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .func(flatMap(x -> Stream.of("A" + x)))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("A1", "A2", "A3"), li);
    }


    @Test
    public void test_flatMap() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3"))
                .flatMap(x -> Stream.of("A" + x))
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("A1", "A2", "A3"), li);
    }

    @Test
    public void takeWhile() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3", "", "5", "6"))
                .takeWhile(x -> !x.isEmpty())
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "2", "3"), li);
    }

    @Test
    public void dropWhile() {
        List<String> li = ExStream.of(Stream.of("1", "2", "3", "", "5", "6"))
                .dropWhile(x -> !x.isEmpty())
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("", "5", "6"), li);
    }


    @Test
    public void distinctByKey() {
        List<String> li = ExStream.of(Stream.of("1", "2", "2", "4", "5", "5", "6"))
                .distinctByKey(Function.identity())
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "2", "4", "5", "6"), li);
    }

    @Test
    public void mapWithIndex() {
        List<Integer> li = ExStream.of(Stream.of("1", "2", "3", "4", "5"))
                .mapWithIndex((index, x) -> index)
                .stream()
                .collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList(0, 1, 2, 3, 4), li);
    }

    @Test
    public void forEachWithIndex() {
        AtomicInteger i = new AtomicInteger();
        ExStream.of(Stream.of("1", "2", "3", "4", "5"))
                .forEachWithIndex((index, x) ->
                        Assertions.assertThat(index)
                                .isEqualTo(i.getAndIncrement())
                );
    }

    @Test
    public void memoized() {
        MemoizedClass m = Mockito.spy(new MemoizedClass());

        List<String> li = ExStream.of(Stream.of(1, 2, 3, 4, 3, 1, 5))
                .memoized(m::convert)
                .stream()
                .collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList("1", "2", "3", "4", "3", "1", "5"), li);
        Mockito.verify(m, Mockito.times(5)).convert(Mockito.any(Integer.class));
    }

    @Test
    public void innerJoin() {
        List<Tuple2<Integer, String>> li = ExStream.of(1, 2, 3)
                .innerJoin(Stream.of("1", "2", "3", "4"), on(x -> String.valueOf(x), Function.identity()))
                .stream()
                .collect(Collectors.toList());

        Assertions.assertThat(li)
                .containsExactly(Tuple.of(1, "1"), Tuple.of(2, "2"), Tuple.of(3, "3"));
    }

    @Test
    public void cached() {
        MemoizedClass m = Mockito.spy(new MemoizedClass());
        ExStream<String> cachedStream = ExStream.of(1, 2, 3)
                .func(map(m::convert))
                .cached();

        List<String> l1 = cachedStream.stream().collect(Collectors.toList());
        List<String> l2 = cachedStream.stream().collect(Collectors.toList());

        Assert.assertEquals(Lists.newArrayList("1", "2", "3"), l1);
        Assert.assertEquals(Lists.newArrayList("1", "2", "3"), l2);
        Mockito.verify(m, Mockito.times(3)).convert(Mockito.any(Integer.class));
    }

    @Test
    public void no_cached() {
        MemoizedClass m = Mockito.spy(new MemoizedClass());
        ExStream<String> noCachedStream = ExStream.of(1, 2, 3)
                .func(map(m::convert));

        List<String> l1 = noCachedStream.stream().collect(Collectors.toList());
        Assert.assertEquals(Lists.newArrayList("1", "2", "3"), l1);

        Assertions.assertThatThrownBy(() -> noCachedStream.stream().collect(Collectors.toList()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("stream has already been operated upon or closed");
    }


    public static class MemoizedClass {
        public String convert(Integer x) {
            return String.valueOf(x);
        }
    }

    public static class Func {
        public static <T> Function<Stream<T>, Stream<T>> filter(Predicate<? super T> predicate) {
            return s -> s.filter(predicate);
        }

        public static <T, R> Function<Stream<T>, Stream<R>> map(Function<? super T, ? extends R> mapper) {
            return s -> s.map(mapper);
        }

        public static <T, R> Function<Stream<T>, Stream<R>> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
            return s -> s.flatMap(mapper);
        }
    }

}