package com.qa.tests;

import com.qa.dto.UserDTO;
import com.qa.setup.BaseTest;
import com.qa.utils.Helpers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest extends BaseTest {

    @Test
    public void testValidateSchema() {

        get("https://jsonplaceholder.typicode.com/users")
                .then()
                .body(matchesJsonSchemaInClasspath("json/users.json"));

    }

    @Test
    public void testGetAllUsers() {

        UserDTO[] retrievedUsers = given()
                .spec(getReq())
                .when()
                .get("users")
                .then()
                .extract()
                .body()
                .as(UserDTO[].class);

        assertThat(retrievedUsers).isNotEmpty();
        assertThat(retrievedUsers).hasSizeGreaterThan(0);

    }

    @Test
    public void testGetAllUsersNotFound() {

        Response resp = when()
                .get("https://jsonplaceholder.typicode.com/userss")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    public void testGetUserById() {

        UserDTO newUser = new UserDTO()
                .setName("Clementine Bauch")
                .setUsername("Samantha")
                .setEmail("Nathan@yesenia.net");

        UserDTO[] retrievedUser = given()
                .spec(getReq())
                .param("id", "3")
                .when()
                .get("users")
                .then()
                .extract()
                .body()
                .as(UserDTO[].class);

        assertThat(retrievedUser[0].getName()).isEqualTo(newUser.getName());
        assertThat(retrievedUser[0].getUsername()).isEqualTo(newUser.getUsername());
        assertThat(retrievedUser[0].getEmail()).isEqualTo(newUser.getEmail());

    }

    @Test
    public void testGetUserByIdNotFound() {

        Response resp = when()
                .get("https://jsonplaceholder.typicode.com/users/11")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);

    }
}
