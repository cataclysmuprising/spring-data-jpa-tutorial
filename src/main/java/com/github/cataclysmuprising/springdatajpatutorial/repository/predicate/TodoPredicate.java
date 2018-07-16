package com.github.cataclysmuprising.springdatajpatutorial.repository.predicate;

import com.github.cataclysmuprising.springdatajpatutorial.entity.Todo;
import com.github.cataclysmuprising.springdatajpatutorial.repository.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

public class TodoPredicate {
	private SearchCriteria criteria;

	public BooleanExpression getPredicate() {
		PathBuilder<Todo> entityPath = new PathBuilder<>(Todo.class, "todo");

		if (isNumeric(criteria.getValue().toString())) {
			NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
			int value = Integer.parseInt(criteria.getValue().toString());
			switch (criteria.getOperation()) {
				case ":":
					return path.eq(value);
				case ">":
					return path.goe(value);
				case "<":
					return path.loe(value);
			}
		} else {
			StringPath path = entityPath.getString(criteria.getKey());
			if (criteria.getOperation().equalsIgnoreCase(":")) {
				return path.containsIgnoreCase(criteria.getValue().toString());
			}
		}
		return null;
	}
}
