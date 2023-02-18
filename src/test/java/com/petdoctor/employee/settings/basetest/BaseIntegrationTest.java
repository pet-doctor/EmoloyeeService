package com.petdoctor.employee.settings.basetest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

/**
 * Класс, использующийся как базовая конфигурация для интеграционных тестов.
 */
@TestMethodOrder(value = MethodOrderer.Random.class)
@TestConstructor(autowireMode = ALL)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class BaseIntegrationTest {

    private static final String POSTGRES_IMAGE = "postgres:14.1-alpine3.15";
    private static final String KAFKA_IMAGE = "confluentinc/cp-kafka:6.2.1-10-ubi8";

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER;
    private static final KafkaContainer KAFKA_CONTAINER;

    static {
        KAFKA_CONTAINER = new KafkaContainer(DockerImageName.parse(KAFKA_IMAGE))
                .waitingFor(Wait.defaultWaitStrategy());
        POSTGRES_CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE)
                .waitingFor(Wait.defaultWaitStrategy());
    }

    @Value(value = "${local.server.port}")
    private int localPort;

    @BeforeAll
    static void init() {
        KAFKA_CONTAINER.start();
        POSTGRES_CONTAINER.start();
    }

    @BeforeEach
    void setUp() {
        RestAssured.requestSpecification = given().port(localPort).contentType(JSON);
    }

    @DynamicPropertySource
    static void setContainerProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);

        registry.add("spring.kafka.bootstrap-servers", KAFKA_CONTAINER::getBootstrapServers);
    }
}
