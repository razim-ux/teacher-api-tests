package ru.razim.api.clients;

import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiClient {

    protected <T> T post(String endpoint, Object body, ResponseSpecification responseSpec, Class<T> responseClass) {
        return given()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    protected <T> T postWithToken(String endpoint, Object body, String token, ResponseSpecification responseSpec, Class<T> responseClass) {
        return given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }


    protected <T> T put(String endpoint, Object body, ResponseSpecification responseSpec, Class<T> responseClass) {
        return given()
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    protected <T> T putWithToken(String endpoint, Object body, String token, ResponseSpecification responseSpec, Class<T> responseClass) {
        return given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    protected <T> T deleteWithToken(String endpoint,
                                    String token,
                                    ResponseSpecification responseSpec,
                                    Class<T> responseClass) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    protected <T> T deleteDlcWithToken(String endpoint,
                                       Object body,
                                       String token,
                                       ResponseSpecification responseSpec,
                                       Class<T> responseClass) {
        return given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when()
                .delete(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    protected <T> T get(String endpoint, ResponseSpecification responseSpec, Class<T> responseClass) {
        return given()
                .when()
                .get(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    protected <T> T getWithToken(String endpoint, String token, ResponseSpecification responseSpec, Class<T> responseClass) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then()
                .spec(responseSpec)
                .extract()
                .as(responseClass);
    }

    protected void getWithTokenWithoutBody(String endpoint, String token, ResponseSpecification responseSpec) {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint)
                .then()
                .spec(responseSpec);
    }


}