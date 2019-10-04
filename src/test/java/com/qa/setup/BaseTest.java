package com.qa.setup;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    private static RequestSpecification spec;

//    @BeforeClass
//    public static void setup() {
//        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
//    }

    @BeforeClass
    public static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://jsonplaceholder.typicode.com/")
                .addFilter(new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        RestAssured.reset();
    }

    public static RequestSpecification getSpec() {
        return spec;
    }

    public static void setSpec(RequestSpecification spec) {
        BaseTest.spec = spec;
    }
}
