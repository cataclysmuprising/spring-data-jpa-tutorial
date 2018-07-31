package com.github.cataclysmuprising.springdatajpatutorial.repository.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;

import com.github.cataclysmuprising.springdatajpatutorial.repository.AbstractRepository;

public class AbstractRepositoryImpl<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements AbstractRepository<T, ID> {

	public AbstractRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

}
