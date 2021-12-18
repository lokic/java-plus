package com.github.lokic.javaplus.specification;

import java.util.function.Function;

public class ExpressionSpecification<T> extends CompositeSpecification<T> {

    private final static ExpressionSpecification<?> TRUE_SPEC = new ExpressionSpecification<>(
            x -> true);
    private final static ExpressionSpecification<?> FALSE_SPEC = new ExpressionSpecification<>(
            x -> false);

    private final Function<T, Boolean> expression;

    public ExpressionSpecification(Function<T, Boolean> expression) {
        this.expression = expression;
    }

    @Override
    public boolean isSatisfiedBy(T entity) {
        return expression.apply(entity);
    }


    @SuppressWarnings("unchecked")
    public static <T> Specification<T> trueSpec() {
        return (Specification<T>) TRUE_SPEC;
    }

    @SuppressWarnings("unchecked")
    public static <T> Specification<T> falseSpec() {
        return (Specification<T>) FALSE_SPEC;
    }
}

