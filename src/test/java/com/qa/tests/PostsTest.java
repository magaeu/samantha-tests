package com.qa.tests;

import com.qa.dto.PostDTO;
import com.qa.setup.BaseTest;
import com.qa.utils.Helpers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyData;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static junit.framework.TestCase.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PostsTest extends BaseTest {

    @Test
    public void testValidateSchema() {

        get("https://jsonplaceholder.typicode.com/posts/")
                .then()
                .body(matchesJsonSchemaInClasspath("json/posts.json"));

    }

    @Test
    public void testGetAllPosts() {

        PostDTO[] retrievedPosts = given()
                .spec(getReq())
                .when()
                .get("posts")
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
                .get("https://jsonplaceholder.typicode.com/postss")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    public void testGetPostById() {

        PostDTO post = new PostDTO()
                .setUserId(3)
                .setId(21)
                .setTitle("asperiores ea ipsam voluptatibus modi minima quia sint")
                .setBody("repellat aliquid praesentium dolorem quo\nsed totam minus non itaque\nnihil labore molestiae sunt dolor eveniet hic recusandae veniam\ntempora et tenetur expedita sunt");

        PostDTO[] retrievedPosts = given()
                .spec(getReq())
                .param("id", "21")
                .when()
                .get("posts")
                .then()
                .extract()
                .response()
                .as(PostDTO[].class);

        assertThat(retrievedPosts.length).isGreaterThan(0);
        assertThat(retrievedPosts[0].getUserId()).isEqualTo(post.getUserId());
        assertThat(retrievedPosts[0].getId()).isEqualTo(post.getId());

    }

    @Test
    public void testGetPostByIdNotFound() {

        Response resp = when()
                .get("https://jsonplaceholder.typicode.com/posts/101")
                .then()
                .spec(getResp())
                .extract()
                .response();

        assertThat(resp.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);

    }
    @Test
    public void testGetPostsByUserId() {

        PostDTO userPost = new PostDTO()
                .setUserId(3)
                .setId(21)
                .setTitle("asperiores ea ipsam voluptatibus modi minima quia sint")
                .setBody("repellat aliquid praesentium dolorem quo\nsed totam minus non itaque\nnihil labore molestiae sunt dolor eveniet hic recusandae veniam\ntempora et tenetur expedita sunt");


        PostDTO[] retrievedPosts = given()
                .spec(getReq())
                .queryParam("userId", "3")
                .when()
                .get("posts")
                .then()
                .extract()
                .response()
                .as(PostDTO[].class);

        assertThat(retrievedPosts.length).isGreaterThan(0);
        assertThat(retrievedPosts[0].getUserId()).isEqualTo(userPost.getUserId());
        assertThat(retrievedPosts[0].getTitle()).isEqualTo(userPost.getTitle());
        assertThat(retrievedPosts[0].getBody()).isEqualTo(userPost.getBody());

    }

    @Test
    public void testGetPostsByUserIdNotExists() {

        Response resp = when()
                .get("https://jsonplaceholder.typicode.com/posts?userId=11")
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
