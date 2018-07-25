package com.github.cataclysmuprising.springdatajpatutorial.serviceTests;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.cataclysmuprising.springdatajpatutorial.criteria.StudentCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.dto.StudentDTO;
import com.github.cataclysmuprising.springdatajpatutorial.service.StudentService;
import com.github.cataclysmuprising.springdatajpatutorial.unitTests.BaseUnitTest;
import com.github.cataclysmuprising.springdatajpatutorial.util.common.ObjectMapperUtil;

public class StudentServiceTest extends BaseUnitTest {
	private static final Logger testLogger = LogManager.getLogger("testLogs." + StudentServiceTest.class.getName());

	@Autowired
	private StudentService service;

	@Before
	public void init() {
		// insert single
		StudentDTO dto1 = new StudentDTO();
		dto1.setName("Mg Mg");
		service.create(dto1);

		// insert multi
		StudentDTO dto2 = new StudentDTO();
		dto2.setName("Hla Hla");

		StudentDTO dto3 = new StudentDTO();
		dto3.setName("U Mya");
		service.createAll(Arrays.asList(dto2, dto3));

	}

	@Test
	public void findById() {
		StudentDTO result = service.findById(1l);
		testLogger.info("Result Entry by fetching with ID: {} ==> {}", 1l, result);
	}

	@Test
	public void findByCriteria() {
		StudentCriteria criteria = new StudentCriteria();
		criteria.setId(1l);
		criteria.setName("Mg Mg");
		criteria.setKeyword("Mg");
		criteria.setIncludeIds(new HashSet<>(Arrays.asList(1l, 2l)));
		criteria.setExcludeIds(new HashSet<>(Arrays.asList(888888l, 999999l)));

		StudentDTO result = service.findByCriteria(criteria);
		testLogger.info("Result Entry by fetching with Criteria: {} ==> {}", criteria, result);
	}

	@Test
	public void findAllByCriteria() {
		StudentCriteria criteria = new StudentCriteria();
		criteria.setIncludeIds(new HashSet<>(Arrays.asList(1l, 2l)));
		criteria.setExcludeIds(new HashSet<>(Arrays.asList(888888l, 999999l)));

		Iterable<StudentDTO> results = service.findAllByCriteria(criteria, null);
		testLogger.info("Result Entries by fetching with Criteria: {}", criteria);
		ObjectMapperUtil.showEntriesOfCollection(results);
	}
}
