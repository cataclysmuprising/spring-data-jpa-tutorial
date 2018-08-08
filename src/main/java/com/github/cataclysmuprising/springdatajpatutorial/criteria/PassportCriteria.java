package com.github.cataclysmuprising.springdatajpatutorial.criteria;

import com.github.cataclysmuprising.springdatajpatutorial.entity.QPassport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class PassportCriteria extends AbstractCriteria {
	private String number;

	@Override
	public Predicate getFilter() {
		QPassport entity = QPassport.passport;
		BooleanBuilder predicate = getCommonFilter(entity._super);

		if (!StringUtils.isBlank(number)) {
			predicate.and(entity.number.eq(number));
		}
		// define for keyword search
		if (!StringUtils.isBlank(keyword)) {
			predicate.and(entity.number.containsIgnoreCase(keyword));
		}
		return predicate;
	}
}
