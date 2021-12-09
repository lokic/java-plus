package com.github.lokic.javaplus.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.github.lokic.javaplus.Either;
import org.junit.Test;

public class CompositeSpecificationValidationTest {

    CompositeSpecificationValidation<Integer, String> isNotNull = new CompositeSpecificationValidation<Integer, String>() {
        @Override
        public Either<Integer, String> validatedBy(String entity) {
            if (entity == null) {
                return Either.left(400);
            }
            return Either.right(entity);
        }
    };


    @Test
    public void isSatisfiedBy() {
        assertFalse(new SpecificationAdapter<>(isNotNull).isSatisfiedBy(null));
        assertTrue(new SpecificationAdapter<>(isNotNull).isSatisfiedBy(""));
        assertTrue(new SpecificationAdapter<>(isNotNull).isSatisfiedBy("xx"));
    }

    @Test
    public void validatedBy() {
        assertEquals(Either.left(400), isNotNull.validatedBy(null));
        assertEquals(Either.right(""), isNotNull.validatedBy(""));
        assertEquals(Either.right("xx"), isNotNull.validatedBy("xx"));
    }
}