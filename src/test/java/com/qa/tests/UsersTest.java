package com.qa.tests;

import com.qa.dto.UserDTO;
import com.qa.setup.BaseTest;
import com.qa.utils.Helpers;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersTest extends BaseTest {

    @Test
    public void testGetUserById() {

        UserDTO newUser = new UserDTO()
                .setName("Clementine Bauch")
                .setUsername("Samantha")
                .setEmail("Nathan@yesenia.net");

        UserDTO[] retrievedUser = given()
                .spec(getSpec())
                .param("id", "3")
                .when()
                .get("users")
                .then()
                .spec(Helpers.checkStatusOKAndContentTypeJson())
                .extract()
                .body()
                .as(UserDTO[].class);

        assertThat(retrievedUser[0].getName()).isEqualTo(newUser.getName());
        assertThat(retrievedUser[0].getUsername()).isEqualTo(newUser.getUsername());
        assertThat(retrievedUser[0].getEmail()).isEqualTo(newUser.getEmail());
    }

    @Test
    public void testGetUserByIdEmpty() {

        UserDTO[] retrievedUser = given()
                .spec(getSpec())
                .param("id", "11")
                .when()
                .get("users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(UserDTO[].class);

        assertThat(retrievedUser).isEmpty();
    }
}
