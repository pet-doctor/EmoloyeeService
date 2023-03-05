package com.petdoctor.employee.settings.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@UtilityClass
public class RestRequestSteps {

    public Response sendPostRequest(String path, Object requestBody) {
        return RestAssured.given()
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(requestBody)
                .post(path)
                .then()
                .extract()
                .response();
    }

    public Response sendDeleteRequest(String path, Map<String, String> mapParams) {
        return RestAssured.given()
                .params(mapParams)
                .delete(path)
                .then()
                .extract()
                .response();
    }
}
