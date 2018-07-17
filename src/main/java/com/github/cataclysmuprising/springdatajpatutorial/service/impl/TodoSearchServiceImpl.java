package com.github.cataclysmuprising.springdatajpatutorial.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.cataclysmuprising.springdatajpatutorial.dto.TodoDTO;
import com.github.cataclysmuprising.springdatajpatutorial.mapper.TodoMapper;
import com.github.cataclysmuprising.springdatajpatutorial.model.Todo;
import com.github.cataclysmuprising.springdatajpatutorial.predicate.TodoPredicates;
import com.github.cataclysmuprising.springdatajpatutorial.repository.TodoRepository;
import com.github.cataclysmuprising.springdatajpatutorial.service.TodoSearchService;

/**
 * @author Petri Kainulainen
 */
@Service
public class TodoSearchServiceImpl implements TodoSearchService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoSearchServiceImpl.class);

	private final TodoRepository repository;

	@Autowired
	public TodoSearchServiceImpl(TodoRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	@Override
	public Page<TodoDTO> findBySearchTerm(String searchTerm, Pageable pageRequest) {
		LOGGER.info("Finding todo entries by search term: {} and page request: {}", searchTerm, pageRequest);

		Page<Todo> searchResultPage = repository.findAll(TodoPredicates.titleOrDescriptionContainsIgnoreCase(searchTerm), pageRequest);

		LOGGER.info("Found {} todo entries. Returned page {} contains {} todo entries", searchResultPage.getTotalElements(), searchResultPage.getNumber(),
				searchResultPage.getNumberOfElements());

		return TodoMapper.mapEntityPageIntoDTOPage(pageRequest, searchResultPage);
	}
}
