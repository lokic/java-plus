package com.github.lokic.javaplus.specification;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NotSpecificationTest {

    @Test
    public void isSatisfiedBy() {
        assertTrue(
            new NotSpecification<String>(ExpressionSpecification.falseSpec()).isSatisfiedBy(""));
        assertFalse(
            new NotSpecification<String>(ExpressionSpecification.trueSpec()).isSatisfiedBy(""));
    }
}