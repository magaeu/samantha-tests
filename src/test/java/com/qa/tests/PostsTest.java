package com.qa.tests;

import com.qa.setup.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.*;
import static org.hamcrest.Matchers.equalTo;

public class PostsTest extends BaseTest {

    @Test
    public void testGetPostByUserId() {

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("userId", "1")
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        String jsonBody = response.getBody().asString();

        try {
            JSONArray postsArray = new JSONArray(jsonBody);
            assertNotNull(postsArray);
            assertTrue(postsArray.length() > 0);
        } catch (JSONException ex) {
            fail(ex.getLocalizedMessage());
        }
    }
}
