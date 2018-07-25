package com.github.cataclysmuprising.springdatajpatutorial.service.impl;

import com.github.cataclysmuprising.springdatajpatutorial.criteria.StudentCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.domain.Student;
import com.github.cataclysmuprising.springdatajpatutorial.dto.StudentDTO;
import com.github.cataclysmuprising.springdatajpatutorial.repository.StudentRepository;
import com.github.cataclysmuprising.springdatajpatutorial.service.StudentService;
import com.github.cataclysmuprising.springdatajpatutorial.util.common.ObjectMapperUtil;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger serviceLogger = LogManager.getLogger("serviceLogs." + StudentServiceImpl.class);

	private final StudentRepository repository;

	@Autowired
	private JPAQueryFactory factory;

	@Autowired
	StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	@Override
	public StudentDTO findById(Long id) {
		serviceLogger.info("Finding student entry by using id: {}", id);

		Student studentEntry = findEntryById(id);
		serviceLogger.info("Found student entry: {}", studentEntry);

		return ObjectMapperUtil.mapEntityIntoDTO(studentEntry, StudentDTO.class);
	}

	@Transactional(readOnly = true)
	@Override
	public StudentDTO findByCriteria(StudentCriteria criteria) {
		serviceLogger.info("Finding student entry by using criteria : {}", criteria);

		Optional<Student> entry = repository.findOne(criteria.getFilter());
		serviceLogger.info("Found student entry: {}", entry);

		return ObjectMapperUtil.mapEntityIntoDTO(entry.orElseThrow(EntityNotFoundException::new), StudentDTO.class);
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<StudentDTO> findAllByCriteria(StudentCriteria criteria, Pageable pager) {
		serviceLogger.info("Finding student entry by using criteria : {}", criteria);
		Iterable<Student> entries;
		if (pager != null) {
			entries = repository.findAll(criteria.getFilter(), pager);
		}
		else {
			entries = repository.findAll(criteria.getFilter());
		}
		serviceLogger.info("Found student entry: {}", entries);

		return ObjectMapperUtil.mapEntityIntoDTO(entries, StudentDTO.class);
	}

	@Transactional
	@Override
	public StudentDTO create(StudentDTO dto) {
		serviceLogger.info("Creating a new student entry by using information: {}", dto);

		Student created = ObjectMapperUtil.mapDTOIntoEntity(dto, Student.class);
		created = repository.save(created);
		serviceLogger.info("Created a new student entry: {}", created);

		return ObjectMapperUtil.mapEntityIntoDTO(created, StudentDTO.class);
	}

	@Transactional
	@Override
	public Iterable<StudentDTO> createAll(Iterable<StudentDTO> dtos) {
		serviceLogger.info("Creating a new student entries by using information: - ");
		ObjectMapperUtil.showEntriesOfCollection(dtos);

		Iterable<Student> entities = ObjectMapperUtil.mapDTOIntoEntity(dtos, Student.class);
		entities = repository.saveAll(entities);

		return ObjectMapperUtil.mapEntityIntoDTO(entities, StudentDTO.class);
	}

	@Transactional
	@Override
	public StudentDTO update(StudentDTO updatedStudentEntry) {
		serviceLogger.info("Updating the information of a student entry by using information: {}", updatedStudentEntry);
		Student updated = findEntryById(updatedStudentEntry.getId());
		// updated.update(updatedStudentEntry.getTitle(), updatedStudentEntry.getDescription());

		// We need to flush the changes or otherwise the returned object
		// doesn't contain the updated audit information.
		repository.flush();

		serviceLogger.info("Updated the information of the student entry: {}", updated);

		return ObjectMapperUtil.mapEntityIntoDTO(updated, StudentDTO.class);
	}

	@Transactional
	@Override
	public StudentDTO delete(Long id) {
		serviceLogger.info("Deleting a student entry with id: {}", id);

		Student deleted = findEntryById(id);
		serviceLogger.debug("Found student entry: {}", deleted);

		repository.delete(deleted);
		serviceLogger.info("Deleted student entry: {}", deleted);

		return ObjectMapperUtil.mapEntityIntoDTO(deleted, StudentDTO.class);
	}

	private Student findEntryById(Long id) {
		Optional<Student> studentResult = repository.findById(id);
		return studentResult.orElseThrow(EntityNotFoundException::new);
	}

}
