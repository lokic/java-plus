package com.github.lokic.javaplus.property;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Property1Test {

    @Test
    public void test_of() {
        assertEquals(TrafficLight.RED, TrafficLight.OF_CODE.of(0));
        assertEquals(TrafficLight.YELLOW, TrafficLight.OF_CODE.of(1));
        assertEquals(TrafficLight.GREEN, TrafficLight.OF_CODE.of(2));
        assertNull(TrafficLight.OF_CODE.of(-1));
        assertNull(TrafficLight.OF_CODE.of(3));
    }

    @Test(expected = IllegalStateException.class)
    public void test_requireOf() {
        TrafficLight.OF_CODE.requireOf(3);
    }

    @Test
    public void test_optOf() {
        assertEquals(Optional.of(TrafficLight.RED), TrafficLight.OF_CODE.optOf(0));
        assertEquals(Optional.empty(), TrafficLight.OF_CODE.optOf(3));
    }

    enum TrafficLight {
        RED(0),
        YELLOW(1),
        GREEN(2);

        private final int code;

        private TrafficLight(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }


        public static final Property1<TrafficLight, Integer> OF_CODE = new Property1<>(TrafficLight.class, TrafficLight::getCode);
    }
}