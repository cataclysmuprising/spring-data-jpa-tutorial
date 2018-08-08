package com.github.cataclysmuprising.springdatajpatutorial.repository.impl;

import com.github.cataclysmuprising.springdatajpatutorial.criteria.StudentCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.entity.Passport;
import com.github.cataclysmuprising.springdatajpatutorial.repository.PassportRepository;
import com.github.cataclysmuprising.springdatajpatutorial.util.common.UpdateItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Set;

import static com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext.ENTITY_MANAGER_FACTORY;

@Repository
public class PassportRepositoryImpl extends AbstractRepositoryImpl<Passport, Long> implements PassportRepository {
	private EntityManager entityManager;

	public PassportRepositoryImpl(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManager entityManager) {
		super(new JpaMetamodelEntityInformation<>(Passport.class, entityManager.getMetamodel()), entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public long update(Set<UpdateItem> updateItems, StudentCriteria criteria) {
		return 0;
	}
}
