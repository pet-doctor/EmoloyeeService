package com.petdoctor.employee.tests.integration.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.petdoctor.employee.model.entity.VetClinicEntity;
import com.petdoctor.employee.repository.VetClinicRepository;
import com.petdoctor.employee.settings.basetest.BaseIntegrationTest;
import com.petdoctor.employee.settings.steps.RestRequestSteps;
import com.petdoctor.employee.settings.testdata.VetClinicTestData.*;
import com.petdoctor.employee.util.TestsUtils;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.*;

@Sql(scripts = {
        "classpath:sql/clean_up.sql",
        "classpath:sql/init_vet_clinic_tests.sql"
})
@DisplayName(value = "Интеграционные тесты API VetClinicController")
@RequiredArgsConstructor
public class VetClinicControllerTests extends BaseIntegrationTest {

    private static final String URI = "/petdoctor/employee/vetclinic";

    private final VetClinicRepository vetClinicRepository;

    @Nested
    @DisplayName(value = "Интеграционные тесты API RegisterVetClinic")
    class RegisterVetClinicTests {
        private static final String REGISTRATION_REQUEST = "/register";

        @Test
        @Description(value = "Register vet clinic")
        @DisplayName(value = "Save vet clinic to database")
        void registerVetClinicUsingValidParamAndNonexistentIdTest() {

            JsonNode body = TestsUtils.getFromFile(Request.I_CASE_DATA.getValue(), JsonNode.class);

            Response response = RestRequestSteps.sendPostRequest(URI + REGISTRATION_REQUEST, body);

            assertAll(
                    () -> assertThat(response.statusCode()).isEqualTo(OK.value()),
                    () -> assertThat(vetClinicRepository
                            .findVetClinicEntityById(1L)
                            .orElse(new VetClinicEntity()))
                            .isEqualTo(ExpectedData.I_EXPECTED_DATA.getEntity())
            );
        }

        @Test
        @Description(value = "Register vet clinic")
        @DisplayName(value = "Don't save vet clinic to data base because one of the param is null")
        void registerVetClinicUsingInValidParamTest() {

            JsonNode body = TestsUtils.getFromFile(Request.II_CASE_DATA.getValue(), JsonNode.class);

            Response response = RestRequestSteps.sendPostRequest(URI + REGISTRATION_REQUEST, body);

            assertAll(
                    () -> assertThat(response.statusCode()).isEqualTo(INTERNAL_SERVER_ERROR.value()),
                    () -> assertThat((String) response.path("code"))
                            .isEqualTo("500"),
                    () -> assertThat((String) response.path("status"))
                            .isEqualTo("Error"),
                    () -> assertThat((String) response.path("message"))
                            .isNotNull()
                            .contains("Failed to complete request"));
        }

        @Test
        @Description(value = "Register vet clinic")
        @DisplayName(value = "Don't save vet clinic to data base because vet clinic with the id is already exist")
        void registerVetClinicUsingValidParamAndExistingIdTest() {

            JsonNode body = TestsUtils.getFromFile(Request.III_CASE_DATA.getValue(), JsonNode.class);

            Response response = RestRequestSteps.sendPostRequest(URI + REGISTRATION_REQUEST, body);

            assertAll(
                    () -> assertThat(response.statusCode()).isEqualTo(BAD_REQUEST.value()),
                    () -> assertThat((String) response.path("code"))
                            .isEqualTo("400"),
                    () -> assertThat((String) response.path("status"))
                            .isEqualTo("Bad request"),
                    () -> assertThat((String) response.path("message"))
                            .isNotNull()
                            .contains("Invalid request"));
        }
    }
}