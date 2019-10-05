package com.qa.tests;

import com.qa.setup.BaseTest;
import com.qa.utils.Helpers;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.*;

public class PostsTest extends BaseTest {

    @Test
    public void testGetPostByUserId() {

        Response response = given()
                .spec(getReq())
                .queryParam("userId", "1")
                .when()
                .get("posts")
                .then()
                .spec(Helpers.checkStatusOKAndContentTypeJson())
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
