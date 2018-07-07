package com.github.cataclysmuprising.springdatajpatutorial.repository;

import com.github.cataclysmuprising.springdatajpatutorial.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	Todo findByTitle(String title);

	long countByTitle(String title);

	Stream<Todo> findByTitleContains(String keyword);
}
