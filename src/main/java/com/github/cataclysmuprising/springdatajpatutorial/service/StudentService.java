package com.github.cataclysmuprising.springdatajpatutorial.service;

import com.github.cataclysmuprising.springdatajpatutorial.dto.StudentDTO;

public interface StudentService {
	StudentDTO create(StudentDTO newEntry);

	StudentDTO findById(Long id);

	StudentDTO update(StudentDTO updateEntry);

	StudentDTO delete(Long id);
}
