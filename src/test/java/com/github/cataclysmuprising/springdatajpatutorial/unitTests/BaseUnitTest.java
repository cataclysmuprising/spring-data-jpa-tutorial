package com.github.cataclysmuprising.springdatajpatutorial.unitTests;

import com.github.cataclysmuprising.springdatajpatutorial.SpringDataJpaTutorialApplication;
import com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringDataJpaTutorialApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional(value = PrimaryPersistenceContext.TRANSACTION_MANAGER, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
@Rollback
public class BaseUnitTest {
}
