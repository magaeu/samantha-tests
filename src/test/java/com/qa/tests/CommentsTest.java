package com.qa.tests;

import com.qa.dto.CommentDTO;
import com.qa.setup.BaseTest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class CommentsTest extends BaseTest {

    @Test
    public void testValidateSchema() {

        get("https://jsonplaceholder.typicode.com/comments/")
                .then()
                .body(matchesJsonSchemaInClasspath("json/comments.json"));

    }

    @Test
    public void testGetAllComments() {

        CommentDTO[] retrievedComments = given()
                .spec(getReq())
                .when()
                .get("comments")
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
                .get("https://jsonplaceholder.typicode.com/commentss")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(resp.getHeader("server")).isEqualTo("cloudflare");

    }

    @Test
    public void testGetCommentsByPostId() {

        CommentDTO comment = new CommentDTO()
                .setPostId(21)
                .setId(101)
                .setName("perspiciatis magnam ut eum autem similique explicabo expedita")
                .setEmail("Lura@rod.tv")
                .setBody("ut aut maxime officia sed aliquam et magni autem\nveniam repudiandae nostrum odio enim eum optio aut\nomnis illo quasi quibusdam inventore explicabo\nreprehenderit dolor saepe possimus molestiae");

        CommentDTO[] retrievedComments = given()
                .spec(getReq())
                .param("postId", "21")
                .when()
                .get("comments")
                .then()
                .extract()
                .response()
                .as(CommentDTO[].class);

        assertThat(retrievedComments.length).isGreaterThan(0);
        assertThat(retrievedComments[0].getPostId()).isEqualTo(comment.getPostId());
        assertThat(retrievedComments[0].getEmail()).isEqualTo(comment.getEmail());
        assertThat(retrievedComments[0].getBody()).isEqualTo(comment.getBody());

    }

    @Test
    public void testGetCommentsPostNotExists() {

        Response resp = when()
                .get("https://jsonplaceholder.typicode.com/comments?postId=101")
                .then()
                .spec(getResp())
                .extract()
                .response();

        String jsonBody = resp.getBody().asString();
        JSONArray jsonArray = new JSONArray(jsonBody);

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(jsonArray).isEmpty();
    }

}
