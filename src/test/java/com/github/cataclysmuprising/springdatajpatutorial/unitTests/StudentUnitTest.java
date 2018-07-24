package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.cataclysmuprising.springdatajpatutorial.domain.Student;
import com.github.cataclysmuprising.springdatajpatutorial.repository.StudentRepository;

public class StudentUnitTest extends BaseUnitTest {
	private static final Logger testLogger = LogManager.getLogger("testLogs." + StudentUnitTest.class.getName());

	@Autowired
	private StudentRepository repository;

	@Before
	public void init() {
		Student entity1 = Student.getBuilder().name("Mg Mg").build();
		repository.save(entity1);

		Student entity2 = Student.getBuilder().name("Hla Hla").build();
		repository.save(entity2);

	}

	@Test
	public void findAll() {
		List<Student> results = repository.findAll();
		testLogger.info("Total Records ==> " + results.size());
	}
}
