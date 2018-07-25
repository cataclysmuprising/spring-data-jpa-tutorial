package com.github.cataclysmuprising.springdatajpatutorial.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "mjr_student")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Student extends AbstractEntity {

	@NotBlank
	@Column(nullable = false)
	private String name;

}