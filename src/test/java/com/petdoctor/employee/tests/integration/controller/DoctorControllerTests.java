package com.petdoctor.employee.tests.integration.controller;

import com.petdoctor.employee.settings.basetest.BaseIntegrationTest;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.jdbc.Sql;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.http.HttpStatus.OK;

//@Sql(scripts = {
//        "classpath:sql/clean_up.sql",
//        "classpath:sql/init_depersonalization_tests.sql"
//})
//@DisplayName(value = "Интеграционные тесты API DoctorController")
//@RequiredArgsConstructor
public class DoctorControllerTests extends BaseIntegrationTest {

//    @Test
//    @Description(value = "Регистрируем доктора")
//    @DisplayName(value = "Сохраняем доктора в базу данных")
//    void registerDoctorTest() {
//
//        String userId = I_CASE_DATA.getUserId();
//        Map<String, String> params = Map.of("userId", userId);
//
//        Response response = RestRequestSteps.sendDeleteRequest(URI + DEPERSONALIZATION_REQUEST, params);
//
//        assertAll(
//                () -> assertThat(response.statusCode()).isEqualTo(OK.value()),
//                () -> assertThat(response.getBody().asString()).isNullOrEmpty(),
//                () -> assertThat(dataManager.isExistUserData(Long.parseLong(userId))).isFalse());
//    }
}
