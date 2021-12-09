package com.github.lokic.javaplus.specification;

public class OrSpecification<T> extends CompositeSpecification<T> {

    private final Specification<T> leftSpecification;
    private final Specification<T> rightSpecification;

    public OrSpecification(Specification<T> leftSpecification,
        Specification<T> rightSpecification) {
        this.leftSpecification = leftSpecification;
        this.rightSpecification = rightSpecification;
    }

    @Override
    public boolean isSatisfiedBy(T entity) {
        return leftSpecification.isSatisfiedBy(entity)
            || rightSpecification.isSatisfiedBy(entity);
    }
}
