package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import com.github.cataclysmuprising.springdatajpatutorial.CommonTestBase;
import com.github.cataclysmuprising.springdatajpatutorial.criteria.PassportCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.entity.Passport;
import com.github.cataclysmuprising.springdatajpatutorial.repository.PassportRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PassportUnitTest extends CommonTestBase {
	private static final Logger testLogger = LogManager.getLogger("testLogs." + PassportUnitTest.class.getName());

	@Autowired
	private PassportRepository repository;

	@Before
	public void init() {
		Passport entity1 = new Passport();
		entity1.setNumber("1234");
		repository.save(entity1);
		Passport entity2 = new Passport();
		entity2.setNumber("5678");
		repository.save(entity2);
	}

	@Test
	public void findAll() {
		PassportCriteria criteria = new PassportCriteria();
		Iterable<Passport> results = repository.findAll(criteria.getFilter());
		results.forEach(result -> testLogger.info("Results ==> " + result));
	}
}
