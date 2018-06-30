package com.github.cataclysmuprising.springdatajpatutorial.service.impl;

import com.github.cataclysmuprising.springdatajpatutorial.repository.TodoRepository;
import com.github.cataclysmuprising.springdatajpatutorial.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {
	private TodoRepository todoRepository;

	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
}
