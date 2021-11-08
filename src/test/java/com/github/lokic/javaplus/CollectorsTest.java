package com.github.lokic.javaplus;

import com.github.lokic.javaplus.tuple.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CollectorsTest {

    @Test
    public void test_distinctFirstPut() {
        Assertions.assertThat(
                Stream.of("A", "B", "A", "C")
                        .collect(Collectors.Distinct.distinctFirstPut()))
                .containsExactly("A", "B", "C");
    }

    @Test
    public void test_distinctLastPut() {
        Assertions.assertThat(
                Stream.of("A", "B", "A", "C")
                        .collect(Collectors.Distinct.distinctLastPut()))
                .containsExactly("B", "A", "C");
    }

    @Test
    public void test_distinctFirstPutByKey() {
        Assertions.assertThat(
                Stream.of(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"))
                        .collect(Collectors.Distinct.distinctFirstPutByKey(DataInfo::getKey)))
                .containsExactly(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("C", "C1"));
    }

    @Test
    public void test_distinctLastPutByKey() {
        Assertions.assertThat(
                        Stream.of(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"))
                                .collect(Collectors.Distinct.distinctLastPutByKey(DataInfo::getKey)))
                .containsExactly(new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"));
    }

    @Test
    public void test_toMap() {
        Map<String, String> expectMap = new HashMap<>();
        expectMap.put("A", "A1");
        expectMap.put("B", "B1");
        expectMap.put("C", "C1");
        Assert.assertEquals(
                expectMap,
                Stream.of(Tuple.of("A", "A1"), Tuple.of("B", "B1"), Tuple.of("A", "A2"), Tuple.of("C", "C1"))
                        .collect(Collectors.toMap((k, v) -> k, (k, v) -> v, (a, b) -> a, HashMap::new))
        );
    }

    @Test
    public void test_toMapTupleStream() {
        Assertions.assertThat(
                Stream.of(Tuple.of("A", "A1"), Tuple.of("B", "B1"), Tuple.of("A", "A2"), Tuple.of("C", "C1"))
                        .collect(Collectors.toMapTupleStream((k, v) -> k, (k, v) -> v, (a, b) -> a, HashMap::new))
        ).containsExactly(Tuple.of("A", "A1"), Tuple.of("B", "B1"), Tuple.of("C", "C1"));
    }


    @Data
    @AllArgsConstructor
    static class DataInfo {
        private String key;
        private String value;
    }

}