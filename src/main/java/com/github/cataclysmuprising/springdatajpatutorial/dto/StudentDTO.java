package com.github.cataclysmuprising.springdatajpatutorial.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class StudentDTO extends AbstractDTO {
	private String name;
}
