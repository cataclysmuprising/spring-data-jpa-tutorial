package com.github.cataclysmuprising.springdatajpatutorial.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "mjr_passport")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Passport extends AbstractEntity {

	@Column(nullable = false)
	private String number;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
	private Student student;
}
