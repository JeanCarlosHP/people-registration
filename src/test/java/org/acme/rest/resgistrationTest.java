package org.acme.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class resgistrationTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/peoples")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}