package com.github.cataclysmuprising.springdatajpatutorial.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;
}