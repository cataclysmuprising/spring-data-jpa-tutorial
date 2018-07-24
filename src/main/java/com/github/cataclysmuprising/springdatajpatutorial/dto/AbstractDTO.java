package com.github.cataclysmuprising.springdatajpatutorial.dto;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = false)
public abstract class AbstractDTO {
	private Long id;

	private Long recordRegId;

	private DateTime recordRegTime;

	private Long recordUpdId;

	private DateTime recordUpdTime;

}
