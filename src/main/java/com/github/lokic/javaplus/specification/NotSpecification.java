package com.github.lokic.javaplus.specification;

public class NotSpecification<T> extends CompositeSpecification<T> {

    private final Specification<T> specification;

    public NotSpecification(Specification<T> specification) {
        this.specification = specification;
    }

    @Override
    public boolean isSatisfiedBy(T entity) {
        return !specification.isSatisfiedBy(entity);
    }
}
