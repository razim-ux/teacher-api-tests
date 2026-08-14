package ru.razim.api.specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestSpec {
    public static final String BASE_URL =
            "http://85.192.34.140:8080";

    public static RequestSpecification requestSpec(){
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .log().ifValidationFails();
    }
}
