package com.github.cataclysmuprising.springdatajpatutorial.repository.predicateBuilder;

import com.github.cataclysmuprising.springdatajpatutorial.repository.SearchCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.repository.predicate.TodoPredicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;

public class TodoPredicateBuilder {
	private List<SearchCriteria> params;

	public TodoPredicateBuilder() {
		params = new ArrayList<>();
	}

	public TodoPredicateBuilder with(
			String key, String operation, Object value) {

		params.add(new SearchCriteria(key, operation, value));
		return this;
	}

	public BooleanExpression build() {
		if (params.size() == 0) {
			return null;
		}

		List<BooleanExpression> predicates = new ArrayList<>();
		TodoPredicate predicate;
		for (SearchCriteria param : params) {
			predicate = new TodoPredicate(param);
			BooleanExpression exp = predicate.getPredicate();
			if (exp != null) {
				predicates.add(exp);
			}
		}

		BooleanExpression result = predicates.get(0);
		for (int i = 1; i < predicates.size(); i++) {
			result = result.and(predicates.get(i));
		}
		return result;
	}
}
