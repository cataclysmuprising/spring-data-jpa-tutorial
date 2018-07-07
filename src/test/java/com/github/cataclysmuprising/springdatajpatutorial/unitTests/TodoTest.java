package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import com.github.cataclysmuprising.springdatajpatutorial.entity.Todo;
import com.github.cataclysmuprising.springdatajpatutorial.repository.TodoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class TodoTest extends BaseUnitTest {

	private static Logger testLogger = LogManager.getLogger("testLogs." + TodoTest.class);

	@Autowired
	private TodoRepository todoRepository;

	@Before
	public void setup() {
		Todo todo1 = new Todo();
		todo1.setTitle("sample1");
		todo1.setDescription("Sample Description 1");
		todo1.setCreationTime(LocalDateTime.now());
		todo1.setModificationTime(LocalDateTime.now());
		todo1.setVersion(1l);
		todoRepository.save(todo1);

		Todo todo2 = new Todo();
		todo2.setTitle("sample2");
		todo2.setDescription("Sample Description 2");
		todo2.setCreationTime(LocalDateTime.now());
		todo2.setModificationTime(LocalDateTime.now());
		todo2.setVersion(1l);
		todoRepository.save(todo2);
	}

	@Test
	public void findByTitle() {
		Todo found = todoRepository.findByTitle("sample1");
		Assert.assertEquals("sample1", found.getTitle());
	}

	@Test
	public void findByTitleContains() {
		Stream<Todo> results = todoRepository.findByTitleContains("sample");
		results.forEach(result -> testLogger.info(result));
	}
}
