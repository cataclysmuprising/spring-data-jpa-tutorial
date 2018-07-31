package com.github.cataclysmuprising.springdatajpatutorial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Predicate;

//@NoRepositoryBean
//public interface AbstractRepository<T, ID> extends Repository<T, ID> {
public interface AbstractRepository<T, ID> {

	Optional<T> findById(ID id);

	Optional<T> findOne(Predicate predicate);

	Iterable<T> findAll(Predicate predicate);

	Page<T> findAll(Predicate predicate, Pageable pageable);

	<S extends T> S save(S entity);

	<S extends T> S saveAndFlush(S entity);

	<S extends T> List<S> saveAll(Iterable<S> entities);

	long count(Predicate predicate);

	void delete(T entity);

	void deleteById(ID id);

	void deleteAll(Iterable<? extends T> entities);

	void flush();

}
