package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import com.github.cataclysmuprising.springdatajpatutorial.model.Todo;
import com.github.cataclysmuprising.springdatajpatutorial.repository.TodoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
	}
}
