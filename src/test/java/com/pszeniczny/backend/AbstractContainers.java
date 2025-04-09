package com.pszeniczny.backend;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
public class AbstractContainers {
    @Container
    public final static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("postgres-test")
            .withUsername("patrykpszeniczny")
            .withPassword("password");

    @DynamicPropertySource
    private static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
    }

    protected static DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName(postgresqlContainer.getDriverClassName())
                .url(postgresqlContainer.getJdbcUrl())
                .username(postgresqlContainer.getUsername())
                .password(postgresqlContainer.getPassword())
                .build();
    }

    protected static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    protected static final Faker FAKER = new Faker();
}
