package com.qa.tests;

import com.qa.setup.BaseTest;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class UsersTest extends BaseTest {

    @Test
    public void testGetUserById() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", "3")
        .when()
            .get("users/{id}")
        .then()
            .statusCode(200)
            .body("username", equalTo("Samantha"));
    }
}
