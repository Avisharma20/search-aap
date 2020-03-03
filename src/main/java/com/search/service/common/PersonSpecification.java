package com.search.service.common;

import com.search.service.beans.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PersonSpecification implements Specification<Student> {
	
	private Student filter;
	public PersonSpecification(Student filter) {
		super();
		this.filter = filter;
	}
	public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> cq,
			CriteriaBuilder cb) {
		Predicate p = cb.disjunction();
		if (filter.getFirstName() != null) {
			//p.getExpressions().add(cb.equal(root.get("firstName"), filter.getFirstName()));
			p.getExpressions().add(cb.like(cb.lower(root.get("firstName")), getContainsLikePattern(filter.getFirstName())));
		}

		if (filter.getEmail() != null) {
			p.getExpressions().add(cb.and(
					cb.equal(root.get("lastName"), filter.getLastName()),
					cb.like(root.get("email"), filter.getEmail())
			));
		}
		/*if(filter.getEmail()!=null){
			p.getExpressions().add(cb.equal(root.get("email"), filter.getEmail()));
		}*/

		return p;
		
	}
	private static String getContainsLikePattern(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return "%";
		}
		else {
			return "%" + searchTerm.toLowerCase() + "%";
		}
	}
	
}
