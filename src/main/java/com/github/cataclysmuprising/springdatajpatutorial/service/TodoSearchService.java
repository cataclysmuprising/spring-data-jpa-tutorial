package com.github.cataclysmuprising.springdatajpatutorial.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.cataclysmuprising.springdatajpatutorial.dto.TodoDTO;

/**
 * This service provides finder methods for {@link net.petrikainulainen.springdata.jpa.todo.Todo} objects.
 *
 * @author Petri Kainulainen
 */
public interface TodoSearchService {

	/**
	 * Finds todo entries whose title or description contains the given search term. This search is case insensitive.
	 * 
	 * @param searchTerm
	 *            The search term.
	 * @param pageRequest
	 *            The information of the requested page.
	 * @return
	 */
	Page<TodoDTO> findBySearchTerm(String searchTerm, Pageable pageRequest);
}
