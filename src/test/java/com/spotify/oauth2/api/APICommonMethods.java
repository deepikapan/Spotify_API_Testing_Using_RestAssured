package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.Map;

import static com.spotify.oauth2.api.Routes.API_ROUTE;
import static com.spotify.oauth2.api.Routes.TOKEN_ROUTE;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class APICommonMethods {

    static Response responsePlayList;

    public static Response post(String path, String token, Object requestPlayList) {
        responsePlayList =
                given(getRequestSpec()).
                        body(requestPlayList).
                        header("Authorization", "Bearer " +token).
                        when().
                        post(path).
                        then().
                        spec(getResponseSpec()).
                        extract().
                        response();
        return responsePlayList;
    }

    public static Response get(String path, String token) {
        responsePlayList =
                given(getRequestSpec()).
                        header("Authorization", "Bearer " +token).
                        when().
                        get(path).
                        then().
                        spec(getResponseSpec()).
                        extract().
                        response();
        return responsePlayList;
    }

    public static Response update(String path, String token, Object updateRequestPlayList) {
        return (given(getRequestSpec()).
                body(updateRequestPlayList).
                header("Authorization", "Bearer " +token).
                when().
                put(path).
                then().
                extract().
                response());
    }
public static Response postAccount(Map<String, String> formParams){
    Response response =
            given(postAccountRequestSpec(formParams)).
                    when().
                    post(API_ROUTE+TOKEN_ROUTE).
                    then().spec(getResponseSpec()).
                    extract().
                    response();
    return response;
}
}
