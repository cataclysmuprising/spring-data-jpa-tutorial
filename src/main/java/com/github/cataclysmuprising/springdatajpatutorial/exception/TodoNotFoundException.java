package com.github.cataclysmuprising.springdatajpatutorial.exception;

/**
 * This exception is thrown when a todo entry is not found by using the given id.
 *
 * @author Petri Kainulainen
 */
public class TodoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4879046416449477092L;
	private final Long id;

	public TodoNotFoundException(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
