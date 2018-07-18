package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.cataclysmuprising.springdatajpatutorial.model.Todo;
import com.github.cataclysmuprising.springdatajpatutorial.predicate.TodoPredicates;
import com.github.cataclysmuprising.springdatajpatutorial.repository.TodoRepository;

public class TodoUnitTest extends BaseUnitTest {

	private static final Logger testLogger = LogManager.getLogger("testLogs." + TodoUnitTest.class.getName());

	@Autowired
	private TodoRepository repository;

	@Before
	public void init() {
		Todo todo1 = Todo.getBuilder().title("Title 1").description("Sample Description 1").build();
		repository.save(todo1);

		Todo todo2 = Todo.getBuilder().title("Title 2").description("Sample Description 2").build();
		repository.save(todo2);
	}

	@Test
	public void findAll() {
		List<Todo> results = repository.findAll();
		testLogger.info("Total Records ==> " + results.size());
		repository.count(TodoPredicates.titleOrDescriptionContainsIgnoreCase("Title 1"));
	}

	@Test
	public void findByTitleOrDescription() {
		Optional<Todo> result = repository.findOne(TodoPredicates.titleOrDescriptionContainsIgnoreCase("Title 1"));
		testLogger.info("Result match with Title 'Title 1' ==> " + result.get());
	}
}
