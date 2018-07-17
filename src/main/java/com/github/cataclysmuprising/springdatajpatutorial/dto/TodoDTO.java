package com.github.cataclysmuprising.springdatajpatutorial.dto;

import java.time.ZonedDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Petri Kainulainen
 */
public final class TodoDTO {

	private String createdByUser;

	private ZonedDateTime creationTime;

	private String description;

	private Long id;

	private String modifiedByUser;

	private ZonedDateTime modificationTime;

	private String title;

	public TodoDTO() {
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public ZonedDateTime getCreationTime() {
		return creationTime;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public ZonedDateTime getModificationTime() {
		return modificationTime;
	}

	public String getTitle() {
		return title;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public void setCreationTime(ZonedDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public void setModificationTime(ZonedDateTime modificationTime) {
		this.modificationTime = modificationTime;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("createdByUser", this.createdByUser).append("creationTime", this.creationTime).append("description", this.description)
				.append("id", this.id).append("modifiedByUser", this.modifiedByUser).append("modificationTime", this.modificationTime).append("title", this.title).toString();
	}
}
