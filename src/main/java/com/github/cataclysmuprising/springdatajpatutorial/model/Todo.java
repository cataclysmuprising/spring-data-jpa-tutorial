package com.github.cataclysmuprising.springdatajpatutorial.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import static com.github.cataclysmuprising.springdatajpatutorial.util.common.PreCondition.*;

/**
 * This entity class contains the information of a single todo entry and the methods that are used to create new todo entries and to modify the information of an existing todo entry.
 *
 * @author Petri Kainulainen
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "todos")
public final class Todo {

	static final int MAX_LENGTH_DESCRIPTION = 500;
	static final int MAX_LENGTH_TITLE = 100;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_by_user", nullable = false)
	@CreatedBy
	private String createdByUser;

	@Column(name = "creation_time", nullable = false)
	@CreatedDate
	private DateTime creationTime;

	@Column(name = "description", length = MAX_LENGTH_DESCRIPTION)
	private String description;

	@Column(name = "modified_by_user", nullable = false)
	@LastModifiedBy
	private String modifiedByUser;

	@Column(name = "modification_time")
	@LastModifiedDate
	private DateTime modificationTime;

	@Column(name = "title", nullable = false, length = MAX_LENGTH_TITLE)
	private String title;

	@Version
	private long version;

	/**
	 * Required by Hibernate.
	 */
	private Todo() {
	}

	private Todo(Builder builder) {
		this.title = builder.title;
		this.description = builder.description;
	}

	/**
	 * This entity is so simple that you don't really need to use the builder pattern (use a constructor instead). I use the builder pattern here because it makes the code a bit more easier to read.
	 */
	public static class Builder {
		private String description;
		private String title;

		private Builder() {
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Todo build() {
			Todo build = new Todo(this);
			build.requireValidTitleAndDescription(build.getTitle(), build.getDescription());

			return build;
		}
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(String createdByUser) {
		this.createdByUser = createdByUser;
	}

	public DateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(DateTime creationTime) {
		this.creationTime = creationTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(String modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	public DateTime getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(DateTime modificationTime) {
		this.modificationTime = modificationTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public void update(String newTitle, String newDescription) {
		requireValidTitleAndDescription(newTitle, newDescription);

		this.title = newTitle;
		this.description = newDescription;
	}

	private void requireValidTitleAndDescription(String title, String description) {
		notNull(title, "Title cannot be null.");
		notEmpty(title, "Title cannot be empty.");
		isTrue(title.length() <= MAX_LENGTH_TITLE, "The maximum length of the title is <%d> characters.", MAX_LENGTH_TITLE);

		isTrue((description == null) || (description.length() <= MAX_LENGTH_DESCRIPTION), "The maximum length of the description is <%d> characters.", MAX_LENGTH_DESCRIPTION);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("createdByUser", this.createdByUser).append("creationTime", this.creationTime).append("description", this.description)
				.append("id", this.id).append("modifiedByUser", this.modifiedByUser).append("modificationTime", this.modificationTime).append("title", this.title)
				.append("version", this.version).toString();
	}
}
