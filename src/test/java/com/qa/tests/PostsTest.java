package com.qa.tests;

import com.qa.dto.PostDTO;
import com.qa.setup.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.junit.Test;

import static com.qa.utils.Helpers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class PostsTest extends BaseTest {

    @Test
    public void testValidateSchema() {

        get(BASE_URL + POSTS_RESOURCE)
                .then()
                .body(matchesJsonSchemaInClasspath(POSTS_JSON_PATH));

    }

    @Test
    public void testGetAllPosts() {

        PostDTO[] retrievedPosts = given()
                .spec(getReq())
                .when()
                .get(POSTS_RESOURCE)
                .then()
                .extract()
                .body()
                .as(PostDTO[].class);

        assertThat(retrievedPosts).isNotEmpty();
        assertThat(retrievedPosts.length).isGreaterThan(0);

    }

    @Test
    public void testGetAllPostsNotFound() {

        Response resp = when()
                .get(BASE_URL + "/postss")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(resp.getHeader("server")).isEqualTo(SERVER_NAME);

    }

    @Test
    public void testGetPostById() {

        PostDTO[] retrievedPosts = given()
                .spec(getReq())
                .param("id", EXISTING_POST_ID)
                .when()
                .get(POSTS_RESOURCE)
                .then()
                .extract()
                .response()
                .as(PostDTO[].class);

        assertThat(retrievedPosts.length).isGreaterThan(0);
        assertThat(retrievedPosts[0].getUserId()).isEqualTo(EXISTING_POST.getUserId());
        assertThat(retrievedPosts[0].getId()).isEqualTo(EXISTING_POST.getId());

    }

    @Test
    public void testGetPostByIdNotFound() {

        Response resp = when()
                .get(BASE_URL + POSTS_RESOURCE + NON_EXISTING_POST)
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(resp.getHeader("server")).isEqualTo(SERVER_NAME);

    }
    @Test
    public void testGetPostsByUserId() {

        PostDTO[] retrievedPosts = given()
                .spec(getReq())
                .queryParam("userId", EXISTING_USER_ID)
                .when()
                .get(POSTS_RESOURCE)
                .then()
                .extract()
                .response()
                .as(PostDTO[].class);

        assertThat(retrievedPosts.length).isGreaterThan(0);
        assertThat(retrievedPosts[0].getUserId()).isEqualTo(EXISTING_POST.getUserId());
        assertThat(retrievedPosts[0].getTitle()).isEqualTo(EXISTING_POST.getTitle());
        assertThat(retrievedPosts[0].getBody()).isEqualTo(EXISTING_POST.getBody());

    }

    @Test
    public void testGetPostsByUserIdNotExists() {

        Response resp = when()
                .get(BASE_URL + POSTS_USER_ID_RESOURCE + NON_EXISTING_USER)
                .then()
                .spec(getResp())
                .extract()
                .response();

        String jsonBody = resp.getBody().asString();
        JSONArray jsonArray = new JSONArray(jsonBody);

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(jsonArray).isEmpty();

    }

    @Test
    public void testGetPostOfSpecificUser() {

        String userId = given()
                .spec(getReq())
                .when()
                .get(USERS_RESOURCE)
                .then()
                .extract()
                .jsonPath()
                .getString("find { it.username == 'Samantha' }.id");

        PostDTO[] postsUser = given()
                .spec(getReq())
                .queryParam(USER_ID_PARAM, userId)
                .when()
                .get(POSTS_RESOURCE)
                .then()
                .extract()
                .response()
                .as(PostDTO[].class);

        assertThat(postsUser.length).isGreaterThan(0);
        assertThat(postsUser.length).isEqualTo(10);
        assertThat(postsUser).extracting(PostDTO::getUserId).containsOnly(Integer.valueOf(userId));

    }
}
