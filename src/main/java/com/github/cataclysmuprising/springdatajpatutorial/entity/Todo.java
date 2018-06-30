package com.github.cataclysmuprising.springdatajpatutorial.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "todos")
public final class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_time", nullable = false)
	private LocalDateTime creationTime;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "modification_time")
	private LocalDateTime modificationTime;

	@Column(name = "title", nullable = false, length = 100)
	private String title;

	@Version
	private long version;
}
