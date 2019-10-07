package com.qa.tests;

import com.qa.dto.UserDTO;
import com.qa.setup.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static com.qa.utils.Helpers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest extends BaseTest {

    @Test
    public void testValidateSchema() {

        get(BASE_URL + USERS_RESOURCE)
                .then()
                .body(matchesJsonSchemaInClasspath(USERS_JSON_PATH));

    }

    @Test
    public void testGetAllUsers() {

        UserDTO[] retrievedUsers = given()
                .spec(getReq())
                .when()
                .get(USERS_RESOURCE)
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
                .get(BASE_URL + "/userss")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    public void testGetUserById() {

        UserDTO[] retrievedUser = given()
                .spec(getReq())
                .param("id", EXISTING_USER_ID)
                .when()
                .get(USERS_RESOURCE)
                .then()
                .extract()
                .body()
                .as(UserDTO[].class);

        assertThat(retrievedUser[0].getName()).isEqualTo(EXISTING_USER.getName());
        assertThat(retrievedUser[0].getUsername()).isEqualTo(EXISTING_USER.getUsername());
        assertThat(retrievedUser[0].getEmail()).isEqualTo(EXISTING_USER.getEmail());

    }

    @Test
    public void testGetUserByIdNotFound() {

        Response resp = when()
                .get(BASE_URL + USERS_RESOURCE + NON_EXISTING_USER)
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);

    }
}
