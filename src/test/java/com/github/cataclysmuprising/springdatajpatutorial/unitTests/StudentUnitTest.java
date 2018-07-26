package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import com.github.cataclysmuprising.springdatajpatutorial.CommonTestBase;
import com.github.cataclysmuprising.springdatajpatutorial.criteria.StudentCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.entity.Student;
import com.github.cataclysmuprising.springdatajpatutorial.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentUnitTest extends CommonTestBase {
    private static final Logger testLogger = LogManager.getLogger("testLogs." + StudentUnitTest.class.getName());

    @Autowired
    private StudentRepository repository;

    @Before
    public void init() {
        Student entity1 = new Student();
        entity1.setName("Mg Mg");
        repository.save(entity1);

        Student entity2 = new Student();
        entity2.setName("Hla Hla");
        repository.save(entity2);

    }

    @Test
    public void findAll() {
        StudentCriteria criteria = new StudentCriteria();
        Iterable<Student> results = repository.findAll(criteria.getFilter());
        testLogger.info("Total Records ==> " + results);
    }
}
