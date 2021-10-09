package com.github.lokic.javaplus;

import org.junit.Assert;
import org.junit.Test;

import java.io.Serializable;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TypesTest {

    @Test
    public void test_getGeneric() {
        assertEquals(TestEvent.class, Types.getGeneric(new TestEventHandler(), EventHandler.class));
        assertEquals(TestEvent.class, Types.getGeneric(new EventHandler<TestEvent>() {
            @Override
            public void handle(TestEvent event) {

            }
        }, EventHandler.class));
        assertEquals(TestEvent.class, Types.getGeneric((EventHandler<TestEvent>) event -> {
        }, EventHandler.class));


        assertEquals(TestEvent.class, Types.getGeneric(new TestEventHandler2(), EventHandler2.class));
        assertEquals(TestEvent.class, Types.getGeneric(new EventHandler2<TestEvent>() {
            @Override
            public void handle1(TestEvent event) {

            }

            @Override
            public void handle2(TestEvent event) {

            }
        }, EventHandler2.class));
    }

    @Test
    public void test_getClass() {
        String s = "abc";
        Class<String> clazz = Types.getClass(s);
        Assert.assertEquals(String.class, clazz);

        Optional<String> sOpt = Optional.of("abc");
        Class<Optional<String>> clazz2 = Types.getClass(sOpt);
        Assert.assertEquals(Optional.class, clazz2);
    }

    public static class TestEvent implements Event {

    }

    public static class TestEventHandler implements EventHandler<TestEvent> {

        private static final long serialVersionUID = -6407605257261824286L;

        @Override
        public void handle(TestEvent event) {

        }
    }


    public static class TestEventHandler2 implements EventHandler2<TestEvent> {

        @Override
        public void handle1(TestEvent event) {

        }

        @Override
        public void handle2(TestEvent event) {

        }
    }

    public interface Event {

    }

    // must have Serializable
    public interface EventHandler<E extends Event> extends Serializable {
        void handle(E event);
    }


    public interface EventHandler2<E extends Event> extends Serializable {
        void handle1(E event);

        void handle2(E event);
    }
}