package com.github.cataclysmuprising.springdatajpatutorial.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SearchCriteria {
	private String key;
	private String operation;
	private Object value;
}
