package com.github.lokic.javaplus;

import com.github.lokic.javaplus.tuple.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.entry;

public class OtherCollectorsTest {

    @Test
    public void test_distinctFirstPut() {
        Assertions.assertThat(
                        Stream.of("A", "B", "A", "C")
                                .collect(OtherCollectors.Distinct.distinctFirstPut()))
                .containsExactly("A", "B", "C");
    }

    @Test
    public void test_distinctLastPut() {
        Assertions.assertThat(
                        Stream.of("A", "B", "A", "C")
                                .collect(OtherCollectors.Distinct.distinctLastPut()))
                .containsExactly("B", "A", "C");
    }

    @Test
    public void test_distinctFirstPutByKey() {
        Assertions.assertThat(
                        Stream.of(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"))
                                .collect(OtherCollectors.Distinct.distinctFirstPutByKey(DataInfo::getKey)))
                .containsExactly(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("C", "C1"));
    }

    @Test
    public void test_distinctLastPutByKey() {
        Assertions.assertThat(
                        Stream.of(new DataInfo("A", "A1"), new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"))
                                .collect(OtherCollectors.Distinct.distinctLastPutByKey(DataInfo::getKey)))
                .containsExactly(new DataInfo("B", "B1"), new DataInfo("A", "A2"), new DataInfo("C", "C1"));
    }

    @Test
    public void test_toMap() {
        Map<String, String> result = Stream.of(Tuple.of("A", "A1"), Tuple.of("B", "B1"), Tuple.of("A", "A2"), Tuple.of("C", "C1"))
                .collect(OtherCollectors.toMap((k, v) -> k, (k, v) -> v, (a, b) -> a, HashMap::new));

        Assertions.assertThat(result)
                .containsExactly(entry("A", "A1"), entry("B", "B1"), entry("C", "C1"));
    }

    @Test
    public void test_toMapTupleStream() {
        Assertions.assertThat(
                Stream.of(Tuple.of("A", "A1"), Tuple.of("B", "B1"), Tuple.of("A", "A2"), Tuple.of("C", "C1"))
                        .collect(OtherCollectors.toMapTupleStream((k, v) -> k, (k, v) -> v, (a, b) -> a, HashMap::new))
        ).containsExactly(Tuple.of("A", "A1"), Tuple.of("B", "B1"), Tuple.of("C", "C1"));
    }

    @Test
    public void test_toMapEntryStream() {
        Assertions.assertThat(
                Stream.of(Tuple.of("A", "A1"), Tuple.of("B", "B1"), Tuple.of("A", "A2"), Tuple.of("C", "C1"))
                        .collect(OtherCollectors.toMapEntryStream((k, v) -> k, (k, v) -> v, (a, b) -> a, HashMap::new))
        ).containsExactly(entry("A", "A1"), entry("B", "B1"), entry("C", "C1"));
    }

    @Test
    public void testAveragingBigDecimal() {
        BigDecimal res = Stream.of(new BigDecimal("2"), new BigDecimal("4"))
                .collect(OtherCollectors.averagingBigDecimal(Function.identity(), 0, RoundingMode.HALF_UP));
        Assertions.assertThat(res)
                .isEqualByComparingTo(new BigDecimal("3"));

        BigDecimal res2 = Stream.of(new BigDecimal("2"))
                .collect(OtherCollectors.averagingBigDecimal(Function.identity(), 0, RoundingMode.HALF_UP));
        Assertions.assertThat(res2)
                .isEqualByComparingTo(new BigDecimal("2"));

        BigDecimal res3 = Stream.<BigDecimal>of()
                .collect(OtherCollectors.averagingBigDecimal(Function.identity(), 0, RoundingMode.HALF_UP));
        Assertions.assertThat(res3)
                .isEqualByComparingTo(new BigDecimal("0"));
    }

    @Test
    public void testSummingBigDecimal() {
        BigDecimal res = Stream.of(new BigDecimal("2"), new BigDecimal("4"))
                .collect(OtherCollectors.summingBigDecimal(Function.identity()));
        Assertions.assertThat(res)
                .isEqualByComparingTo(new BigDecimal("6"));
    }

    @Data
    @AllArgsConstructor
    static class DataInfo {
        private String key;
        private String value;
    }

}