package com.github.lokic.javaplus.validation;

import com.github.lokic.javaplus.Either;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpecificationAdapterTest {

    @Test
    public void isSatisfiedBy() {

        Validation<IllegalArgumentException, String> isNotNull = entity -> {
            if (entity == null) {
                return Either.left(new IllegalArgumentException("not null"));
            }
            return Either.right(entity);
        };

        assertFalse(new SpecificationAdapter<>(isNotNull).isSatisfiedBy(null));
        assertTrue(new SpecificationAdapter<>(isNotNull).isSatisfiedBy(""));
        assertTrue(new SpecificationAdapter<>(isNotNull).isSatisfiedBy("xx"));
    }
}