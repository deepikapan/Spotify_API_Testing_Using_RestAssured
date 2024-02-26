package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.APICommonMethods;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Routes.PLAYLIST_ROUTE;
import static com.spotify.oauth2.api.Routes.USER_ROUTE;

public class PlayListAPI {

    static String userId = ConfigLoader.getInstance().getUser_Id();

    static String access_Token = TokenManager.getAccessToken();

    public static Response post(Playlist requestPlayList) {
        return APICommonMethods.post(USER_ROUTE+"/" + userId+PLAYLIST_ROUTE, access_Token, requestPlayList);
    }

    public static Response post(String token, Playlist requestPlayList) {
        return APICommonMethods.post(USER_ROUTE+"/" +userId +PLAYLIST_ROUTE, token, requestPlayList);

    }

    public static Response get(String createdId) {
        return APICommonMethods.get(PLAYLIST_ROUTE+"/" +createdId, access_Token);

    }

    public static Response update(Playlist updateRequestPlayList, String createdId) {
        return APICommonMethods.update(PLAYLIST_ROUTE+"/" +createdId, access_Token, updateRequestPlayList);

    }

}
