package com.search.service.common;

public class EulaSpecification<T> extends AbstractSpecification<T> {

    private SpecSearchCriteria criteria;

    public EulaSpecification(final SpecSearchCriteria criteria) {
        super(criteria);

    }
    public SpecSearchCriteria getCriteria() {
        return criteria;
    }
}

