package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.applicationApi.PlayListAPI;
import com.spotify.oauth2.pojo.ErrorMain;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlayListsTest {

    Playlist requestPlayList, updateRequestPlayList, responsePlayList;
    static String createdPlayListID;
    Random randomNumber = new Random();
    int upperBoundForRandom = 100;
    String randomIntNumber;

    @Description("Setting up our request and update API body")
    @BeforeClass
    public void beforeClass() {
        randomIntNumber = Integer.toString(randomNumber.nextInt(upperBoundForRandom));
        requestPlayList = new Playlist()
                .setName("Deepika_PlayList")
                .setDescription("PlayList Desc")
                .setPublic(false);

        updateRequestPlayList = new Playlist()
                .setName("PlayList_RestAssured_Updated")
                .setDescription("Playlist Updated ")
                .setPublic(false);

    }

    @Description("Positive test : Creating Playlist")
    @Test
    public void shouldBeAbleToCreatePlaylist() {

        responsePlayList = PlayListAPI
                .post(requestPlayList)
                .as(Playlist.class);
        createdPlayListID = responsePlayList.getId();
        assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
        assertThat(responsePlayList.getPublic(), equalTo(requestPlayList.getPublic()));

    }

    @Description("Positive test case: Getting playlist")
    @Test
    public void shouldBeAbleToGetNewlyCreatedPlayList() {
        responsePlayList =
                PlayListAPI
                        .get(createdPlayListID)
                        .as(Playlist.class);

        assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
        assertThat(responsePlayList.getPublic(), equalTo(requestPlayList.getPublic()));

    }

    @Description("Positive Test Case: Updating Playlist")
    @Test
    public void shouldBeAbleToUpdatePlayList() {
        PlayListAPI.update(updateRequestPlayList, DataLoader.getInstance().getUpdatePlaylistId());

    }

    @Description("Negative test case: Creating playlist without Name of the playlist")
    @Test
    public void shouldNotBeAbleToCreatePlaylistWithoutName() {
        Playlist requestPlayList = new Playlist().
                setName("").
                setDescription(randomIntNumber + " PlayList WithOutNAme " + randomIntNumber).
                setPublic(false);
        Response response = PlayListAPI.post(requestPlayList);
        ErrorMain errorMain = response.as(ErrorMain.class);
        System.out.println((errorMain.getError().getStatus()));
    }

    @Description("Negative test case: Creating playlist with wrong Access token")
    @Test()
    public void shouldNotBeAbleToCreatePlaylistWithExpiredAccess_Token() {
        String ExpiredAccess_Token = "123456";
        Response response = PlayListAPI.post(ExpiredAccess_Token, requestPlayList);
        ErrorMain errorMain = response.as(ErrorMain.class);

        assertThat(errorMain.getError().getStatus(), equalTo(401));
        assertThat(errorMain.getError().getMessage(), equalTo("Invalid access token"));
    }

}
