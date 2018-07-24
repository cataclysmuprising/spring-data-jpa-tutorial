package com.github.cataclysmuprising.springdatajpatutorial.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.cataclysmuprising.springdatajpatutorial.domain.Student;
import com.github.cataclysmuprising.springdatajpatutorial.dto.StudentDTO;
import com.github.cataclysmuprising.springdatajpatutorial.repository.StudentRepository;
import com.github.cataclysmuprising.springdatajpatutorial.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	private final StudentRepository repository;

	@Autowired
	StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public StudentDTO create(StudentDTO newStudentEntry) {
		LOGGER.info("Creating a new todo entry by using information: {}", newStudentEntry);

		Student created = Student.getBuilder().description(newStudentEntry.getDescription()).title(newStudentEntry.getTitle()).build();

		created = repository.save(created);
		LOGGER.info("Created a new todo entry: {}", created);

		return StudentMapper.mapEntityIntoDTO(created);
	}

	@Transactional
	@Override
	public StudentDTO delete(Long id) {
		LOGGER.info("Deleting a todo entry with id: {}", id);

		Student deleted = findStudentEntryById(id);
		LOGGER.debug("Found todo entry: {}", deleted);

		repository.delete(deleted);
		LOGGER.info("Deleted todo entry: {}", deleted);

		return StudentMapper.mapEntityIntoDTO(deleted);
	}

	@Transactional(readOnly = true)
	@Override
	public List<StudentDTO> findAll() {
		LOGGER.info("Finding all todo entries.");

		List<Student> todoEntries = repository.findAll();

		LOGGER.info("Found {} todo entries", todoEntries.size());

		return StudentMapper.mapEntitiesIntoDTOs(todoEntries);
	}

	@Transactional(readOnly = true)
	@Override
	public StudentDTO findById(Long id) {
		LOGGER.info("Finding todo entry by using id: {}", id);

		Student todoEntry = findStudentEntryById(id);
		LOGGER.info("Found todo entry: {}", todoEntry);

		return StudentMapper.mapEntityIntoDTO(todoEntry);
	}

	@Transactional
	@Override
	public StudentDTO update(StudentDTO updatedStudentEntry) {
		LOGGER.info("Updating the information of a todo entry by using information: {}", updatedStudentEntry);

		Student updated = findStudentEntryById(updatedStudentEntry.getId());
		updated.update(updatedStudentEntry.getTitle(), updatedStudentEntry.getDescription());

		// We need to flush the changes or otherwise the returned object
		// doesn't contain the updated audit information.
		repository.flush();

		LOGGER.info("Updated the information of the todo entry: {}", updated);

		return StudentMapper.mapEntityIntoDTO(updated);
	}

	private Student findStudentEntryById(Long id) {
		Optional<Student> todoResult = repository.findById(id);
		return todoResult.orElseThrow(() -> new StudentNotFoundException(id));
	}

}
