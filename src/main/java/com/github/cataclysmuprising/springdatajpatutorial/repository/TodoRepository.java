package com.github.cataclysmuprising.springdatajpatutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.github.cataclysmuprising.springdatajpatutorial.model.Todo;

/**
 * This repository provides CRUD operations for {@link net.petrikainulainen.springdata.jpa.todo.Todo} objects.
 *
 * @author Petri Kainulainen
 */
public interface TodoRepository extends JpaRepository<Todo, Long>, QuerydslPredicateExecutor<Todo> {

}
