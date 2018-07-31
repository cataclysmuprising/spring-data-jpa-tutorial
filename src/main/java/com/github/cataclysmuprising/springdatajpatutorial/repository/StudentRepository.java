package com.github.cataclysmuprising.springdatajpatutorial.repository;

import com.github.cataclysmuprising.springdatajpatutorial.entity.Student;

public interface StudentRepository extends AbstractRepository<Student, Long> {

	Student fetchStudentByName(String name);

}
