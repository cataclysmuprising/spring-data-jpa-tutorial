package com.github.cataclysmuprising.springdatajpatutorial.domain;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@MappedSuperclass
@ToString(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "record_reg_id", nullable = false)
	@CreatedBy
	private Long recordRegId;

	@Column(name = "record_reg_time", nullable = false)
	@CreatedDate
	private DateTime recordRegTime;

	@Column(name = "record_upd_id", nullable = false)
	@LastModifiedBy
	private Long recordUpdId;

	@Column(name = "record_upd_time")
	@LastModifiedDate
	private DateTime recordUpdTime;

}
