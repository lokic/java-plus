package com.github.lokic.javaplus;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;

public class TypesTest {

    @Test
    public void getGeneric() {
        assertEquals(TestEvent.class, Types.getGeneric(new TestEventHandler(), EventHandler.class));
        assertEquals(TestEvent.class, Types.getGeneric((EventHandler<TestEvent>) event -> {}, EventHandler.class));
    }

    public static class TestEvent implements Event {

    }

    public static class TestEventHandler implements EventHandler<TestEvent> {

        private static final long serialVersionUID = -6407605257261824286L;

        @Override
        public void handle(TestEvent event) {

        }
    }

    public interface Event {

    }

    // must have Serializable
    public interface EventHandler<E extends Event> extends Serializable {
        void handle(E event);
    }
}