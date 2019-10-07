package com.qa.setup;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.qa.utils.Helpers.BASE_URL;

public class BaseTest {

    private static RequestSpecification req;
    private static ResponseSpecification resp;

    @BeforeClass
    public static void requestSpec() {

        req = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @BeforeClass
    public static void responseSpec() {

        resp = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();

    }

    @AfterClass
    public static void tearDown() throws Exception {

        RestAssured.reset();

    }

    public static RequestSpecification getReq() {
        return req;
    }

    public static void setReq(RequestSpecification req) {
        BaseTest.req = req;
    }

    public static ResponseSpecification getResp() {
        return resp;
    }

    public static void setResp(ResponseSpecification resp) {
        BaseTest.resp = resp;
    }
}
