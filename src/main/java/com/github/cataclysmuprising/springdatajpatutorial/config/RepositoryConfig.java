package com.github.cataclysmuprising.springdatajpatutorial.config;

import com.github.cataclysmuprising.springdatajpatutorial.util.common.TestAuditorAwareImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

import static com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext.ENTITY_MANAGER_FACTORY;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class RepositoryConfig {

	@Bean
	public JPAQueryFactory jpaQueryFactory(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManager em) {
		return new JPAQueryFactory(em);
	}

	@Bean("auditorProvider")
	@Profile("test")
	public AuditorAware<Long> auditorProvider() {
		return new TestAuditorAwareImpl();
	}

	// @Bean
	// public ObjectMapper objectMapper() {
	// ObjectMapper objectMapper = new ObjectMapper();
	// objectMapper.registerModule(new JodaModule());
	// objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	// objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	// return objectMapper;
	// }
}
