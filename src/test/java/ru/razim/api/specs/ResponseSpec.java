package ru.razim.api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.lessThan;

public class ResponseSpec {
    public static ResponseSpecification success200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(3000L))
                .build();
    }
    public static ResponseSpecification created201(){
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(3000L))
                .build();
    }
    public static ResponseSpecification badRequest400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(3000L))
                .build();
    }

    public static ResponseSpecification unauthorized401() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(3000L))
                .build();
    }
    public static ResponseSpecification unauthorized401WithoutBody() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectResponseTime(lessThan(3000L))
                .build();
    }


}
