package com.pszeniczny.backend;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TestcontainersTest extends AbstractContainers {

    @Test
    void canStartPostgresDB() {
        assertThat(postgresqlContainer.isCreated()).isTrue();
        assertThat(postgresqlContainer.isRunning()).isTrue();
    }
}
