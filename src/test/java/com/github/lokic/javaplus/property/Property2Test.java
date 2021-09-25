package com.github.lokic.javaplus.property;

import com.github.lokic.javaplus.tuple.Tuple;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Property2Test {


    enum TestEnum {
        ONE_ONE(1, "1"),
        ONE_TWO(1, "2"),
        TWO_ONE(2, "1"),
        ;


        private final int one;
        private final String two;

        TestEnum(int one, String two) {
            this.one = one;
            this.two = two;
        }

        public int getOne() {
            return one;
        }

        public String getTwo() {
            return two;
        }

        public static final Property2<TestEnum, Integer, String> OF = new Property2<>(TestEnum.class, e -> Tuple.of(e.getOne(), e.getTwo()));
    }

    @Test
    public void test_of() {
        assertEquals(TestEnum.ONE_ONE, TestEnum.OF.of(1, "1"));
        assertEquals(TestEnum.ONE_TWO, TestEnum.OF.of(1, "2"));
        assertEquals(TestEnum.TWO_ONE, TestEnum.OF.of(2, "1"));
        assertNull(TestEnum.OF.of(2, "2"));
    }

    @Test
    public void test_optOf() {
        assertEquals(Optional.of(TestEnum.ONE_ONE), TestEnum.OF.optOf(1, "1"));
        assertEquals(Optional.empty(), TestEnum.OF.optOf(2, "2"));
    }

    @Test
    public void test_requireOf() {
        assertEquals(TestEnum.ONE_ONE, TestEnum.OF.requireOf(1, "1"));
        Assertions.assertThatIllegalStateException().isThrownBy(() -> TestEnum.OF.requireOf(2, "2"));
    }
}