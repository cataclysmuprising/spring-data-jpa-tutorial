package com.github.cataclysmuprising.springdatajpatutorial.criteria;

import org.apache.commons.lang3.StringUtils;

import com.github.cataclysmuprising.springdatajpatutorial.domain.QStudent;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentCriteria extends AbstractCriteria {

	private String name;

	@Override
	public Predicate getFilter() {
		QStudent entity = QStudent.student;
		BooleanBuilder predicate = getCommonFilter(entity._super);

		if (!StringUtils.isBlank(name)) {
			predicate.and(entity.name.eq(name));
		}
		// define for keyword search
		if (!StringUtils.isBlank(keyword)) {
			predicate.and(entity.name.containsIgnoreCase(keyword));
		}
		return predicate;
	}

}
