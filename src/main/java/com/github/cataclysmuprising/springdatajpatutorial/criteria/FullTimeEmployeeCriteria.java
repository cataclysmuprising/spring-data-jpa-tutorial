package com.github.cataclysmuprising.springdatajpatutorial.criteria;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.github.cataclysmuprising.springdatajpatutorial.domain.QFullTimeEmployee;
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
public class FullTimeEmployeeCriteria extends AbstractCriteria {

	private String name;
	private BigDecimal salary;

	@Override
	public Predicate getFilter() {
		QFullTimeEmployee entity = QFullTimeEmployee.fullTimeEmployee;
		BooleanBuilder predicate = getCommonFilter(entity._super);

		if (!StringUtils.isBlank(name)) {
			predicate.and(entity.name.eq(name));
		}
		if (salary != null) {
			predicate.and(entity.salary.eq(salary));
		}

		// define for keyword search
		if (!StringUtils.isBlank(keyword)) {
			predicate.and(entity.name.containsIgnoreCase(keyword));
		}
		return predicate;
	}

}
