package com.qa.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

public class Helpers {

    public static ResponseSpecification checkStatusOKAndContentTypeJson() {
       return new ResponseSpecBuilder()
                        .expectStatusCode(HttpStatus.SC_OK)
                        .expectContentType(ContentType.JSON)
                        .build();
    }
}
