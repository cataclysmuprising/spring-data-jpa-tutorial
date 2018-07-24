package com.github.cataclysmuprising.springdatajpatutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.github.cataclysmuprising.springdatajpatutorial.domain.AbstractEntity;

public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, Long>, QuerydslPredicateExecutor<T> {

}
