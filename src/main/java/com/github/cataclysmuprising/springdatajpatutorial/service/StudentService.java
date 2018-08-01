package com.github.cataclysmuprising.springdatajpatutorial.service;

import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.github.cataclysmuprising.springdatajpatutorial.criteria.StudentCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.dto.StudentDTO;
import com.github.cataclysmuprising.springdatajpatutorial.util.common.UpdateItem;

public interface StudentService {

	StudentDTO findById(Long id);

	StudentDTO findByCriteria(StudentCriteria criteria);

	Iterable<StudentDTO> findAllByCriteria(StudentCriteria criteria, Pageable pager);

	StudentDTO create(StudentDTO newEntry);

	Iterable<StudentDTO> createAll(Iterable<StudentDTO> dtos);

	StudentDTO update(StudentDTO updateEntry);

	long update(Set<UpdateItem> updateItems, StudentCriteria criteria);

	StudentDTO delete(Long id);

}
