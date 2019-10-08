package com.qa.tests;

import com.qa.dto.CommentDTO;
import com.qa.setup.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.junit.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static com.qa.utils.Helpers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentsTest extends BaseTest {

    @Test
    public void testValidateSchema() {

        get(BASE_URL + COMMENTS_RESOURCE)
                .then()
                .body(matchesJsonSchemaInClasspath(COMMENTS_JSON_PATH));

    }

    @Test
    public void testGetAllComments() {

        CommentDTO[] retrievedComments = given()
                .spec(getReq())
                .when()
                .get(COMMENTS_RESOURCE)
                .then()
                .extract()
                .body()
                .as(CommentDTO[].class);

        assertThat(retrievedComments).isNotEmpty();
        assertThat(retrievedComments.length).isGreaterThan(0);
        assertThat(retrievedComments.length).isEqualTo(500);

    }

    @Test
    public void testGetAllCommentsNotFound() {

        Response resp = when()
                .get(BASE_URL + "/commentss")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(resp.getHeader("server")).isEqualTo(SERVER_NAME);

    }

    @Test
    public void testGetCommentsByPostId() {

        CommentDTO[] retrievedComments = given()
                .spec(getReq())
                .param(POST_ID_PARAM, EXISTING_POST_ID)
                .when()
                .get(COMMENTS_RESOURCE)
                .then()
                .extract()
                .response()
                .as(CommentDTO[].class);

        assertThat(retrievedComments.length).isGreaterThan(0);
        assertThat(retrievedComments[0].getPostId()).isEqualTo(EXISTING_COMMENT.getPostId());
        assertThat(retrievedComments[0].getEmail()).isEqualTo(EXISTING_COMMENT.getEmail());
        assertThat(retrievedComments[0].getBody()).isEqualTo(EXISTING_COMMENT.getBody());

    }

    @Test
    public void testGetCommentsPostNotExists() {

        Response resp = when()
                .get(BASE_URL + POSTS_USER_ID_RESOURCE + NON_EXISTING_POST)
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
    public void testGetCommentsOfASpecificUsersPost() {

        String userId = given()
                .spec(getReq())
                .when()
                .get(USERS_RESOURCE)
                .then()
                .extract()
                .jsonPath()
                .getString("find { it.username == 'Samantha' }.id");

        String postId = given()
                .spec(getReq())
                .queryParam(USER_ID_PARAM, userId)
                .when()
                .get(POSTS_RESOURCE)
                .then()
                .extract()
                .jsonPath()
                .getList("id")
                .get(0)
                .toString();

        CommentDTO[] commentsPost = given()
                .spec(getReq())
                .queryParam(POST_ID_PARAM, postId)
                .when()
                .get(COMMENTS_RESOURCE)
                .then()
                .extract()
                .response()
                .as(CommentDTO[].class);

        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        assertThat(commentsPost.length).isGreaterThan(0);
        assertThat(commentsPost.length).isEqualTo(5);
        assertThat(commentsPost).extracting(CommentDTO::getPostId).containsOnly(Integer.valueOf(postId));
        Arrays.stream(commentsPost).map(CommentDTO::getEmail).forEach(email -> assertThat(email).matches(pattern));

    }

}
