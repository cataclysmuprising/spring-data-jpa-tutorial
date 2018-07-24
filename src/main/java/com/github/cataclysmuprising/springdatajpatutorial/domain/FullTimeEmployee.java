package com.github.cataclysmuprising.springdatajpatutorial.domain;

import static com.github.cataclysmuprising.springdatajpatutorial.util.common.PreCondition.*;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mjr_fulltime_employee")
@Getter
@Setter
@ToString(callSuper = true)
public class FullTimeEmployee extends AbstractEntity {

	private BigDecimal salary;
	private String name;

	private FullTimeEmployee() {
	}

	private FullTimeEmployee(Builder builder) {
		this.name = builder.name;
		this.salary = builder.salary;
	}

	public static class Builder {
		private String name;
		private BigDecimal salary;

		private Builder() {
		}

		public Builder name(String name) {
			notNull(name, "Name cannot be null.");
			notEmpty(name, "Name cannot be empty.");
			this.name = name;
			return this;
		}

		public Builder salary(BigDecimal salary) {
			notNull(salary, "Title cannot be null.");
			this.salary = salary;
			return this;
		}

		public FullTimeEmployee build() {
			return new FullTimeEmployee(this);
		}
	}

	public static Builder getBuilder() {
		return new Builder();
	}

}
