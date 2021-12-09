package com.github.lokic.javaplus.specification;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrSpecificationTest {

    @Test
    public void isSatisfiedBy() {
        assertTrue(new OrSpecification<String>(ExpressionSpecification.trueSpec(),
            ExpressionSpecification.falseSpec()).isSatisfiedBy(""));
        assertTrue(new OrSpecification<String>(ExpressionSpecification.falseSpec(),
            ExpressionSpecification.trueSpec()).isSatisfiedBy(""));
        assertFalse(new OrSpecification<String>(ExpressionSpecification.falseSpec(),
            ExpressionSpecification.falseSpec()).isSatisfiedBy(""));
        assertTrue(new OrSpecification<String>(ExpressionSpecification.trueSpec(),
            ExpressionSpecification.trueSpec()).isSatisfiedBy(""));
    }
}