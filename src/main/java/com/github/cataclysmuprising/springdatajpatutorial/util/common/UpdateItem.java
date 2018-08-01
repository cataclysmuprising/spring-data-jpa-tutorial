package com.github.cataclysmuprising.springdatajpatutorial.util.common;

import com.querydsl.core.types.Path;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateItem<Z> {
	private Path<Z> key;
	private Z value;

}
