package com.github.cataclysmuprising.springdatajpatutorial.dto;

import org.joda.time.DateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = false)
public abstract class AbstractDTO {
	private Long id;

	@Setter(AccessLevel.NONE)
	private Long recordRegId;

	@Setter(AccessLevel.NONE)
	private DateTime recordRegTime;

	@Setter(AccessLevel.NONE)
	private Long recordUpdId;

	@Setter(AccessLevel.NONE)
	private DateTime recordUpdTime;

}
