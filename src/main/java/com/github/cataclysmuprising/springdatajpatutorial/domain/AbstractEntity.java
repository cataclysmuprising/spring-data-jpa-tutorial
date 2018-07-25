package com.github.cataclysmuprising.springdatajpatutorial.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@ToString
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
