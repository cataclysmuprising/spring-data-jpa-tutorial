package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import com.github.cataclysmuprising.springdatajpatutorial.entity.Todo;
import com.github.cataclysmuprising.springdatajpatutorial.repository.TodoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TodoRepository todoRepository;

	@Test
	public void whenFindByTitle_thenReturnTodo() {
		// given
		Todo todo = new Todo();
		todo.setTitle("sample");
		todo.setDescription("Sample Description");
		todo.setCreationTime(LocalDateTime.now());
		todo.setModificationTime(LocalDateTime.now());
		todo.setVersion(1l);

		entityManager.persist(todo);
		entityManager.flush();

		// when
		Todo found = todoRepository.findByTitle("sample");

		// then
		Assert.assertEquals(found.getTitle(), todo.getTitle());
	}
}
