package com.github.cataclysmuprising.springdatajpatutorial.repository.impl;

import java.util.HashMap;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.stereotype.Repository;

import com.github.cataclysmuprising.springdatajpatutorial.criteria.StudentCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.entity.QStudent;
import com.github.cataclysmuprising.springdatajpatutorial.entity.Student;
import com.github.cataclysmuprising.springdatajpatutorial.repository.StudentRepository;
import com.github.cataclysmuprising.springdatajpatutorial.util.common.UpdateItem;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

@Repository
public class StudentRepositoryImpl extends AbstractRepositoryImpl<Student, Long> implements StudentRepository {

	private EntityManager entityManager;

	private QStudent student = QStudent.student;

	public StudentRepositoryImpl(EntityManager entityManager) {
		super(new JpaMetamodelEntityInformation<>(Student.class, entityManager.getMetamodel()), entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Student fetchStudentByName(String name) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		return queryFactory.selectFrom(student).where(student.name.eq(name)).fetchOne();
	}

	@Override
	public long update(Set<UpdateItem> updateItems, StudentCriteria criteria) {
		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		JPAUpdateClause update = queryFactory.update(student);
		updateItems.forEach(updateItem -> update.set(updateItem.getKey(), updateItem.getValue()));
		update.where(criteria.getFilter()).execute();
		return 0;
	}

	@SuppressWarnings("unchecked")
	private <Z> Z getAttribute(HashMap<String, Object> updateItems, Path<Z> path) {
		return (Z) updateItems.get(path.getMetadata().getElement().toString());
	}

}
