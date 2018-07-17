package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.cataclysmuprising.springdatajpatutorial.model.Todo;
import com.github.cataclysmuprising.springdatajpatutorial.repository.TodoRepository;

public class TodoUnitTest extends BaseUnitTest {

	@Autowired
	private TodoRepository repository;

	@Before
	public void init() {
		Todo todo1 = Todo.getBuilder().title("Title1").description("Sample Description1").build();
		repository.save(todo1);

		Todo todo2 = Todo.getBuilder().title("Title2").description("Sample Description2").build();
		repository.save(todo2);

	}

	@Test
	public void initializationTest() {
		System.out.println("Done ..");
	}

}
