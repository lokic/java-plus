package com.github.lokic.javaplus.specification;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NotSpecificationTest {

    @Test
    public void isSatisfiedBy() {
        assertTrue(
            new NotSpecification<String>(ExpressionSpecification.falseSpec()).isSatisfiedBy(""));
        assertFalse(
            new NotSpecification<String>(ExpressionSpecification.trueSpec()).isSatisfiedBy(""));

        assertTrue(ExpressionSpecification.falseSpec().not().isSatisfiedBy(""));
        assertFalse(ExpressionSpecification.trueSpec().not().isSatisfiedBy(""));
    }
}