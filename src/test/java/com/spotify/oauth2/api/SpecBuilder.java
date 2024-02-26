package com.spotify.oauth2.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static com.spotify.oauth2.api.Routes.*;

public class SpecBuilder {


    public static RequestSpecification getRequestSpec() {
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri(BASE_URI_MAIN_ROUTE).
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addHeader("Content-Type", "application/json").
                log(LogDetail.ALL).
                build();
        return requestSpecification;
    }

    public static ResponseSpecification getResponseSpec() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();
        return responseSpec;
    }

    public static RequestSpecification postAccountRequestSpec(Map<String, String> formParams){
        RequestSpecification postRequestSpecification = new RequestSpecBuilder().
                                                        setBaseUri(BASE_URI_ACCOUNTS_ROUTE).
                                                        setContentType(ContentType.URLENC).
                                                        addFormParams(formParams).build();
        return postRequestSpecification;
    }
}
