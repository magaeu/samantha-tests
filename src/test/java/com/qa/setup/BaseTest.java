package com.qa.setup;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @AfterClass
    public static void tearDown() throws Exception {
        RestAssured.reset();
    }
}
