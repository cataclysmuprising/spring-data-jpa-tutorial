package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.cataclysmuprising.springdatajpatutorial.criteria.FullTimeEmployeeCriteria;
import com.github.cataclysmuprising.springdatajpatutorial.domain.FullTimeEmployee;
import com.github.cataclysmuprising.springdatajpatutorial.repository.FullTimeEmployeeRepository;

public class FullTimeEmployeeUnitTest extends BaseUnitTest {
	private static final Logger testLogger = LogManager.getLogger("testLogs." + FullTimeEmployeeUnitTest.class.getName());

	@Autowired
	private FullTimeEmployeeRepository repository;

	@Before
	public void init() {
		FullTimeEmployee employee1 = FullTimeEmployee.getBuilder().name("Mg Mg").salary(new BigDecimal(100000)).build();
		repository.save(employee1);

		FullTimeEmployee employee2 = FullTimeEmployee.getBuilder().name("Hla Hla").salary(new BigDecimal(250000)).build();
		repository.save(employee2);
	}

	@Test
	public void findAll() {
		List<FullTimeEmployee> results = repository.findAll();
		testLogger.info("Total Records ==> " + results.size());
	}

	@Test
	public void testSearchByCriteria() {
		FullTimeEmployeeCriteria criteria = new FullTimeEmployeeCriteria();
		criteria.setSortProperty("name");
		criteria.setSortType("desc");
		criteria.setIncludeIds(new HashSet<>(Arrays.asList(1l, 2l)));
		criteria.setExcludeIds(new HashSet<>(Arrays.asList(8l, 9l)));
		criteria.setKeyword("Mg");
		// criteria.setId(1l);
		criteria.setRecordRegTimeFrom(DateTime.now().minusDays(1));
		criteria.setRecordRegTimeTo(DateTime.now().plusDays(1));
		Iterable<FullTimeEmployee> results = repository.findAll(criteria.getFilter(), criteria.getPager(0, 1000));
		results.forEach(result -> {
			testLogger.info("==> " + result);
		});
	}
}
