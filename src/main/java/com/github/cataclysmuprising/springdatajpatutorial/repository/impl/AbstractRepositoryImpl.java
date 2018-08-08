package com.github.cataclysmuprising.springdatajpatutorial.repository.impl;

import com.github.cataclysmuprising.springdatajpatutorial.repository.AbstractRepository;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.io.Serializable;

public abstract class AbstractRepositoryImpl<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements AbstractRepository<T, ID> {

	@Resource(name = "auditorProvider")
	protected AuditorAware<Long> auditorProvider;

	public AbstractRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}
}
