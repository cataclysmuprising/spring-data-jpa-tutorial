package com.github.cataclysmuprising.springdatajpatutorial.domain;

import static com.github.cataclysmuprising.springdatajpatutorial.util.common.PreCondition.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mjr_student")
@Getter
@Setter
@ToString(callSuper = true)
public class Student extends AbstractEntity {

	@Column(nullable = false)
	private String name;

	private Student() {
	}

	private Student(Builder builder) {
		this.name = builder.name;
	}

	public static class Builder {
		private String name;

		private Builder() {
		}

		public Builder name(String name) {
			notNull(name, "Name cannot be null.");
			notEmpty(name, "Name cannot be empty.");
			this.name = name;
			return this;
		}

		public Student build() {
			return new Student(this);
		}
	}

	public static Builder getBuilder() {
		return new Builder();
	}

}