package com.github.cataclysmuprising.springdatajpatutorial.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext.PRIMARY_ENTITY_MANAGER_FACTORY;
import static com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext.PRIMARY_TRANSACTION_MANAGER;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableJpaRepositories(
		entityManagerFactoryRef = PRIMARY_ENTITY_MANAGER_FACTORY,
		transactionManagerRef = PRIMARY_TRANSACTION_MANAGER,
		basePackages = {"com.github.cataclysmuprising.springdatajpatutorial.repository"})
public class PrimaryPersistenceContext {
	public static final String PRIMARY_DATASOURCE_CONFIG = "primaryDSConfig";
	public static final String PRIMARY_DATASOURCE = "primaryDataSource";
	public static final String PRIMARY_ENTITY_MANAGER_FACTORY = "primaryEntityManagerFactory";
	public static final String PRIMARY_TRANSACTION_MANAGER = "primaryTransactionManager";

	@Bean(name = PRIMARY_DATASOURCE_CONFIG)
	@ConfigurationProperties(prefix = "datasource.primary")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Primary
	@Bean(name = PRIMARY_DATASOURCE, destroyMethod = "close")
	public HikariDataSource primaryDataSource(@Qualifier(PRIMARY_DATASOURCE_CONFIG) HikariConfig config) {
		return new HikariDataSource(config);
	}

	@Primary
	@Bean(name = PRIMARY_ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier(PRIMARY_DATASOURCE) DataSource primaryDataSource) {
		return builder
				.dataSource(primaryDataSource)
				.packages("com.github.cataclysmuprising.springdatajpatutorial.entity")
				.persistenceUnit("primaryPSTUnit")
				.build();
	}

	@Primary
	@Bean(name = PRIMARY_TRANSACTION_MANAGER)
	public PlatformTransactionManager primaryTransactionManager(
			@Qualifier(PRIMARY_ENTITY_MANAGER_FACTORY) EntityManagerFactory primaryEntityManagerFactory) {
		return new JpaTransactionManager(primaryEntityManagerFactory);
	}
}
