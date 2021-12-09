package com.github.lokic.javaplus.specification;

import static org.junit.Assert.assertEquals;

import com.github.lokic.javaplus.Either;
import org.junit.Test;

public class ValidationAdapterTest {

    @Test
    public void validatedBy() {
        assertEquals(Either.left("error"),
            new ValidationAdapter<>(ExpressionSpecification.falseSpec(), x -> "error").validatedBy(
                ""));
        assertEquals(Either.right(""),
            new ValidationAdapter<>(ExpressionSpecification.trueSpec(), x -> "error").validatedBy(
                ""));
    }
}