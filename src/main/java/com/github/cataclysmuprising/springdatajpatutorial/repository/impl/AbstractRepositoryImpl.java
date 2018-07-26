package com.github.cataclysmuprising.springdatajpatutorial.repository.impl;

import com.github.cataclysmuprising.springdatajpatutorial.repository.AbstractRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class AbstractRepositoryImpl<T, ID extends Serializable> extends QuerydslJpaRepository<T, ID> implements AbstractRepository<T, ID> {

    public AbstractRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public AbstractRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager, EntityPathResolver resolver) {
        super(entityInformation, entityManager, resolver);
    }
}
