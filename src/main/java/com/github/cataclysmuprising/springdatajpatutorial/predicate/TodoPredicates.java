package com.github.cataclysmuprising.springdatajpatutorial.predicate;

import com.github.cataclysmuprising.springdatajpatutorial.model.QTodo;
import com.querydsl.core.types.Predicate;

/**
 * This predicate builder class provides static methods that are used to create <code>Predicate</code> objects which specify the search criteria of dynamic database queries.
 *
 * @author Petri Kainulainen
 */
public final class TodoPredicates {

	/**
	 * Prevent instantiation
	 */
	private TodoPredicates() {
	}

	/**
	 * Creates the search criteria that returns all todo entries whose title or description contains the given search term. The search is case insensitive.
	 *
	 * If the search term is <code>null</code> or empty, the created search criteria will return all todo entries.
	 *
	 * @param searchTerm
	 *            The used search term.
	 * @return
	 */
	public static Predicate titleOrDescriptionContainsIgnoreCase(String searchTerm) {
		if (searchTerm == null || searchTerm.isEmpty()) {
			return QTodo.todo.isNotNull();
		}
		else {
			return QTodo.todo.description.containsIgnoreCase(searchTerm).or(QTodo.todo.title.containsIgnoreCase(searchTerm));
		}
	}
}
