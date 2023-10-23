package com.github.lokic.javaplus;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FunctionsTest {

    @Test
    public void test_mapWithIndex() {
        Assertions.assertThat(
                        Stream.of("A", "B", "C")
                                .map(Functions.mapWithIndex((index, item) -> item + " : " + index))
                                .collect(Collectors.toList()))
                .containsExactly("A : 0", "B : 1", "C : 2");
    }

    @SneakyThrows
    @Test
    public void testToBiConsumer() {
        Method m = TestClass.class.getDeclaredMethod("setId", Long.class);
        BiConsumer<TestClass, Long> f = Functions.toBiConsumer(m, TestClass.class, Long.class);
        TestClass a = new TestClass(123L);
        f.accept(a, 1L);

        Assertions.assertThat(a.getId())
                .isEqualTo(1L);

        f.accept(a, null);
        Assertions.assertThat(a.getId())
                .isEqualTo(null);

    }

    @SneakyThrows
    @Test
    public void testToFunction() {
        Method m = TestClass.class.getDeclaredMethod("getId");
        Function<TestClass, Long> f = Functions.toFunction(m, TestClass.class, Long.class);

        Assertions.assertThat(f.apply(new TestClass(123L)))
                .isEqualTo(123L);
        Assertions.assertThat(f.apply(new TestClass(1L)))
                .isEqualTo(1L);
        Assertions.assertThat(f.apply(new TestClass()))
                .isEqualTo(null);
    }

    public static class TestClass {
        private Long id;

        public TestClass() {

        }

        public TestClass(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}