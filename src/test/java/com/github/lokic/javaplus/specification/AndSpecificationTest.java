package com.github.lokic.javaplus.specification;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AndSpecificationTest {

    @Test
    public void isSatisfiedBy() {
        assertFalse(new AndSpecification<String>(ExpressionSpecification.trueSpec(),
                ExpressionSpecification.falseSpec()).isSatisfiedBy(""));
        assertFalse(new AndSpecification<String>(ExpressionSpecification.falseSpec(),
                ExpressionSpecification.trueSpec()).isSatisfiedBy(""));
        assertFalse(new AndSpecification<String>(ExpressionSpecification.falseSpec(),
                ExpressionSpecification.falseSpec()).isSatisfiedBy(""));
        assertTrue(new AndSpecification<String>(ExpressionSpecification.trueSpec(),
                ExpressionSpecification.trueSpec()).isSatisfiedBy(""));
    }

}