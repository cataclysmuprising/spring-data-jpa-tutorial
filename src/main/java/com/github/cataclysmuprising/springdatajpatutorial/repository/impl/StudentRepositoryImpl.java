package com.github.cataclysmuprising.springdatajpatutorial.repository.impl;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.stereotype.Repository;

import com.github.cataclysmuprising.springdatajpatutorial.entity.QStudent;
import com.github.cataclysmuprising.springdatajpatutorial.entity.Student;
import com.github.cataclysmuprising.springdatajpatutorial.repository.StudentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class StudentRepositoryImpl extends AbstractRepositoryImpl<Student, Long> implements StudentRepository {

	private EntityManager entityManager;

	public StudentRepositoryImpl(EntityManager entityManager) {
		super(new JpaMetamodelEntityInformation<>(Student.class, entityManager.getMetamodel()), entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Student fetchStudentByName(String name) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		QStudent student = QStudent.student;
		return queryFactory.selectFrom(student).where(student.name.eq(name)).fetchOne();
	}

}
