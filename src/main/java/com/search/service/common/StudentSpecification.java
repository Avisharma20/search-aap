package com.search.service.common;

public class StudentSpecification extends AbstractSpecification {

    private SpecSearchCriteria criteria;

    public StudentSpecification(final SpecSearchCriteria criteria) {
        super(criteria);

    }
    public SpecSearchCriteria getCriteria() {
        return criteria;
    }
}

