package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.spotify.oauth2.api.APICommonMethods.postAccount;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;


    public static String getAccessToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing token...");
                Response response = renewAccessToken();
                access_token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            }
            else{
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            throw new RuntimeException("Abort !!! Renew Token Failed");
        }
        return access_token;
    }

    private static Response renewAccessToken(){
        Map<String, String> formParamsForToken = new HashMap<>();
        formParamsForToken.put("grant_type", ConfigLoader.getInstance().getGrant_type());
        formParamsForToken.put("refresh_token", ConfigLoader.getInstance().getRefresh_token());
        formParamsForToken.put("client_id",ConfigLoader.getInstance().getClientID());
        formParamsForToken.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        Response response=
                postAccount(formParamsForToken);

        if(response.getStatusCode() != 200){
            throw new RuntimeException("Abort !!! Renew Token Failed");
        }

    return  response;
    }
}
