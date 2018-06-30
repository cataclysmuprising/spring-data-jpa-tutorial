package com.github.cataclysmuprising.springdatajpatutorial.repository;

import com.github.cataclysmuprising.springdatajpatutorial.entity.Todo;
import org.springframework.data.repository.Repository;

public interface TodoRepository extends Repository<Todo, Long> {
	Todo findByTitle(String title);
}
