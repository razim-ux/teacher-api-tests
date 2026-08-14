package ru.razim.api.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import ru.razim.api.specs.RequestSpec;

public class BaseTest {
    @BeforeAll
    public static void setUp(){
        RestAssured.requestSpecification = RequestSpec.requestSpec();

    }
}
