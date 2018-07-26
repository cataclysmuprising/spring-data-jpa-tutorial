package com.github.cataclysmuprising.springdatajpatutorial.config;

import com.github.cataclysmuprising.springdatajpatutorial.repository.impl.AbstractRepositoryImpl;
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

import static com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext.ENTITY_MANAGER_FACTORY;
import static com.github.cataclysmuprising.springdatajpatutorial.config.PrimaryPersistenceContext.TRANSACTION_MANAGER;

@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableJpaRepositories(entityManagerFactoryRef = ENTITY_MANAGER_FACTORY, transactionManagerRef = TRANSACTION_MANAGER, basePackages = {
        "com.github.cataclysmuprising.springdatajpatutorial.repository"}, repositoryBaseClass = AbstractRepositoryImpl.class)
public class PrimaryPersistenceContext {
    public static final String ENTITY_MANAGER_FACTORY = "primaryEntityManagerFactory";
    public static final String TRANSACTION_MANAGER = "primaryTransactionManager";
    private static final String DATASOURCE_CONFIG = "primaryDSConfig";
    private static final String DATASOURCE = "primaryDataSource";

    @Bean(name = DATASOURCE_CONFIG)
    @ConfigurationProperties(prefix = "datasource.primary")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Primary
    @Bean(name = DATASOURCE, destroyMethod = "close")
    public HikariDataSource primaryDataSource(@Qualifier(DATASOURCE_CONFIG) HikariConfig config) {
        return new HikariDataSource(config);
    }


    @Primary
    @Bean(name = ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier(DATASOURCE) DataSource primaryDataSource) {
        return builder.dataSource(primaryDataSource).packages("com.github.cataclysmuprising.springdatajpatutorial.domain").persistenceUnit("primaryPSTUnit").build();
    }

    @Primary
    @Bean(name = TRANSACTION_MANAGER)
    public PlatformTransactionManager primaryTransactionManager(@Qualifier(ENTITY_MANAGER_FACTORY) EntityManagerFactory primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory);
    }
}
