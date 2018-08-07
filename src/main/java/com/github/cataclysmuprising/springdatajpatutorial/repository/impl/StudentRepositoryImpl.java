package com.github.cataclysmuprising.springdatajpatutorial.repository.impl;

import com.github.cataclysmuprising.springdatajpatutorial.criteria.StudentCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.entity.QStudent;
import com.github.cataclysmuprising.springdatajpatutorial.entity.Student;
import com.github.cataclysmuprising.springdatajpatutorial.repository.StudentRepository;
import com.github.cataclysmuprising.springdatajpatutorial.util.common.UpdateItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Set;

import static com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext.ENTITY_MANAGER_FACTORY;

@Repository
public class StudentRepositoryImpl extends AbstractRepositoryImpl<Student, Long> implements StudentRepository {

    private EntityManager entityManager;

    private QStudent student = QStudent.student;

    public StudentRepositoryImpl(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManager entityManager) {
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
        updateItems.add(new UpdateItem(student.recordUpdId, auditorProvider.getCurrentAuditor().get()));
        updateItems.add(new UpdateItem<>(student.recordUpdTime, DateTime.now()));
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        JPAUpdateClause update = queryFactory.update(student);
        updateItems.forEach(updateItem -> update.set(updateItem.getKey(), updateItem.getValue()));
        update.where(criteria.getFilter()).execute();
        return 0;
    }

}
